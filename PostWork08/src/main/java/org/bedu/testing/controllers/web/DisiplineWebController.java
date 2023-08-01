package org.bedu.testing.controllers.web;

import org.bedu.testing.models.CandidateDTO;
import org.bedu.testing.models.DisciplineDTO;
import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.services.imp.CandidateService;
import org.bedu.testing.services.imp.DisciplineService;
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

@Controller
@RequestMapping("disciplines")
public class DisiplineWebController {

    private final String home="disciplines/";
    private final String index="index";
    private final String form="discipline-form";
    private final String myDTO="disciplineDTO";
    private final String myLis="listaPersonas";
    private final String myMsg="message";
    @Autowired
    private final DisciplineService disciplineService ;

    public DisiplineWebController(DisciplineService disciplineService) {

        this.disciplineService = disciplineService;
    }

    @GetMapping({"","/","/index/","/update","/registro","/edit"})
    public String CandidateWebController(Model model, @Param("keyword") String keyword) {

        if(keyword== null)
            model.addAttribute(myLis, disciplineService.findAllDisciplines());
        else
            model.addAttribute(myLis, disciplineService.findByNameOrDescriptionOrSlug(keyword));

        return home +index;
    }

    @GetMapping("/discipline-form")
    public String AddTutorial( Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute(myDTO, new DisciplineDTO());
        model.addAttribute(myMsg, "Adding a new discipline " + " ");

        return home+form;
    }

    @PostMapping("/registro")
    public ModelAndView registra(@Valid DisciplineDTO disciplineDTO , BindingResult result, Model model) {

        ModelAndView mav = new ModelAndView(home +index);

        try {
            if (!result.hasErrors()){
                this.disciplineService.saveDiscipline (disciplineDTO);
                mav.addObject(myMsg, "The discipline " + disciplineDTO.getName() + " has been added successfully!");
            }

        } catch (Exception e) {
            mav.addObject(myMsg, e.getMessage());
        }
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update( @Valid  DisciplineDTO disciplineDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView(home +form);

        try {

            if (disciplineDTO !=null){
                model.addAttribute(myDTO, disciplineDTO);

                if ( !result.hasErrors() ){
                    if ( disciplineDTO.getId()!= null )
                        model.addAttribute(myMsg, "The discipline " + disciplineDTO.getName()  + " has been updated successfully!");
                    else
                        model.addAttribute(myMsg, "The discipline " + disciplineDTO.getName()  + " has been added successfully!");

                    this.disciplineService.saveDiscipline (disciplineDTO);
                }

                return mav;
            }

            model.addAttribute(myDTO, new DisciplineDTO() );
            model.addAttribute(myMsg, "The discipline that you want update does not exist.");

        } catch (Exception e) {
            model.addAttribute(myMsg, e.getMessage());
        }

        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<DisciplineDTO> disciplineDb = this.disciplineService.findDisciplineById (id);

            if (disciplineDb.isEmpty())
                redirectAttributes.addFlashAttribute(myMsg, "The discipline that you want delete does not exist.");
            else{
                disciplineService.delete (id);
                redirectAttributes.addFlashAttribute(myMsg, "The discipline " + disciplineDb.get().getName() + " has been deleted successfully!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }

        return "redirect:/"+home;
    }

    @GetMapping("/edit/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            Optional<DisciplineDTO> disciplineDb = disciplineService.findDisciplineById (id);

            if (disciplineDb.isEmpty()){
                model.addAttribute(myDTO, new InterviewerDTO());
                model.addAttribute(myMsg, "The discipline that you want update does not exist.");
            }
            else{
                model.addAttribute(myDTO, disciplineDb.get());
                model.addAttribute(myMsg, "Edit discipline " +  disciplineDb.get().getName() + " ");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }
        return home+form;
    }

    @RequestMapping(value={"/**"}, method={RequestMethod.GET, RequestMethod.POST})
    public String allRequests(Model model ,RedirectAttributes redirectAttributes) {

        //model.addAttribute(myDTO, new DisciplineDTO());
        model.addAttribute(myLis, disciplineService.findAllDisciplines ());

        return home+index;
    }

}
