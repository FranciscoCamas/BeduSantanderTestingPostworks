package org.bedu.testing.controllers.web;

import org.bedu.testing.models.InterviewTypeDTO;
import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.services.IInterviewTypeService;
import org.bedu.testing.services.imp.InterviewTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec francisco_camas@hotmail.com
 */

@Controller
@RequestMapping("interviewtypes")
public class InterviewTypeWebController {

    private final String home="interviewtypes/";
    private final String index="index";
    private final String form="interviewtype-form";
    private final String myDTO="interviewTypeDTO";
    private final String myLis="listaPersonas";
    private final String myMsg="message";
    @Autowired
    private final InterviewTypeService interviewTypeService ;

    public InterviewTypeWebController(InterviewTypeService interviewTypeService) {

        this.interviewTypeService = interviewTypeService;
    }

    @GetMapping({"","/","/index/","/update","/registro","/edit"})
    public String CandidateWebController(Model model, @Param("keyword") String keyword) {

        if(keyword== null)
            model.addAttribute(myLis, interviewTypeService.findAllInterviewTypes ());
        else
            model.addAttribute(myLis, interviewTypeService.findByNameOrDescriptionOrSlug (keyword));

        return home +index;
    }

    @GetMapping("/interviewtype-form")
    public String AddTutorial( Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute(myDTO, new InterviewTypeDTO());
        model.addAttribute(myMsg, "Adding a new Interview Type " + " ");

        return home+form;
    }

    @PostMapping("/registro")
    public ModelAndView registra(@Valid InterviewTypeDTO interviewTypeDTO , BindingResult result, Model model) {

        ModelAndView mav = new ModelAndView(home +index);

        try {
            if (!result.hasErrors()){
                this.interviewTypeService.saveInterviewType (interviewTypeDTO);
                mav.addObject(myMsg, "The interview type " + interviewTypeDTO.getName() + " has been added successfully!");
            }

        } catch (Exception e) {
            mav.addObject(myMsg, e.getMessage());
        }
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update( @Valid  InterviewTypeDTO interviewTypeDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView(home +form);

        try {

            if (interviewTypeDTO !=null){
                model.addAttribute(myDTO, interviewTypeDTO);

                if ( !result.hasErrors() ){
                    if ( interviewTypeDTO.getId()!= null )
                        model.addAttribute(myMsg, "The Interview Type " + interviewTypeDTO.getName()  + " has been updated successfully!");
                    else
                        model.addAttribute(myMsg, "The Interview Type " + interviewTypeDTO.getName()  + " has been added successfully!");

                    this.interviewTypeService.saveInterviewType (interviewTypeDTO);
                }

                return mav;
            }

            model.addAttribute(myDTO, new InterviewTypeDTO() );
            model.addAttribute(myMsg, "The Interview Type that you want update does not exist.");

        } catch (Exception e) {
            model.addAttribute(myMsg, e.getMessage());
        }

        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<InterviewTypeDTO> interviewTypeDTODb = this.interviewTypeService.findInterviewTypeById (id);

            if (interviewTypeDTODb.isEmpty())
                redirectAttributes.addFlashAttribute(myMsg, "The Interview Type that you want delete does not exist.");
            else{
                interviewTypeService.delete (id);
                redirectAttributes.addFlashAttribute(myMsg, "The discipline " + interviewTypeDTODb.get().getName() + " has been deleted successfully!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }

        return "redirect:/"+home;
    }

    @GetMapping("/edit/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            Optional<InterviewTypeDTO> interviewTypeDTODb = interviewTypeService.findInterviewTypeById (id);

            if (interviewTypeDTODb.isEmpty()){
                model.addAttribute(myDTO, new InterviewerDTO());
                model.addAttribute(myMsg, "The discipline that you want update does not exist.");
            }
            else{
                model.addAttribute(myDTO, interviewTypeDTODb.get());
                model.addAttribute(myMsg, "Edit discipline " +  interviewTypeDTODb.get().getName() + " ");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }
        return home+form;
    }

    @RequestMapping(value={"/**"}, method={RequestMethod.GET, RequestMethod.POST})
    public String allRequests(Model model ,RedirectAttributes redirectAttributes) {

        //model.addAttribute(myDTO, new DisciplineDTO());
        model.addAttribute(myLis, interviewTypeService.findAllInterviewTypes ());

        return home+index;
    }

}