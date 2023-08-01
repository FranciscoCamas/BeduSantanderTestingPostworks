package org.bedu.testing.controllers.web;

import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.services.imp.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec  francisco_Camas@hotmail.com
 */

@Controller
@RequestMapping("interviewers")
public class InterviewWebController {

    private final String home="interviewers/";
    private final String index="index";
    private final String form="interviewer-form";
    @Autowired
    private final InterviewerService interviewerService;

    public InterviewWebController(InterviewerService interviewerService) {
        this.interviewerService = interviewerService;
    }


    @GetMapping({"","/","/index/","/update","/registro","/edit"})
    public String formularioRegistro(Model model,@Param("keyword") String keyword) {

        if(keyword== null)
          model.addAttribute("listaPersonas", interviewerService.findAllInterviewers());
        else
          model.addAttribute("listaPersonas", interviewerService.findByNameOrEmail(keyword));

        return home +index;
    }

    @GetMapping("/interviewer-form")
    public String AddTutorial( Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("interviewerDTO", new InterviewerDTO());
        model.addAttribute("message", "Adding a new interviewer " + " ");

        return home+form;
    }

    @PostMapping("/registro")
    public ModelAndView registra(@Valid  InterviewerDTO interviewerDTO, BindingResult result, Model model) {

        ModelAndView mav = new ModelAndView(home +index);

        try {
              if (!result.hasErrors()){
                this.interviewerService.saveInterviewer(interviewerDTO);
                mav.addObject("message", "The interviewer " + interviewerDTO.getName() + interviewerDTO.getLast_Name() + " has been added successfully!");
              }

        } catch (Exception e) {
            mav.addObject("message", e.getMessage());
        }
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update( @Valid  InterviewerDTO interviewerDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView(home +form);

        try {

             if (interviewerDTO !=null){
                model.addAttribute("interviewerDTO", interviewerDTO);

                if ( !result.hasErrors() ){

                    if ( interviewerDTO.getId()!= null )
                        model.addAttribute("message", "The interviewer " + interviewerDTO.getName() + interviewerDTO.getLast_Name() + " has been updated successfully!");
                    else
                        model.addAttribute("message", "The interviewer " + interviewerDTO.getName() + interviewerDTO.getLast_Name() + " has been added successfully!");

                    this.interviewerService.saveInterviewer(interviewerDTO);
                }

                return mav;
            }

            model.addAttribute("interviewerDTO", new InterviewerDTO() );
            model.addAttribute("message", "The interviewer that you want update does not exist.");

           } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<InterviewerDTO> interviewerDb = this.interviewerService.findInterviewerById (id);

            if (interviewerDb.isEmpty())
                 redirectAttributes.addFlashAttribute("message", "The interviewer that you want delete does not exist.");
            else{
                  interviewerService.delete (id);
                  redirectAttributes.addFlashAttribute("message", "The interviewer" + interviewerDb.get().getName() + interviewerDb.get().getLast_Name() + " has been deleted successfully!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/"+home;
    }

    @GetMapping("/edit/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            Optional<InterviewerDTO> interviewerDb = interviewerService.findInterviewerById (id);

            if (interviewerDb.isEmpty()){
                model.addAttribute("interviewerDTO", new InterviewerDTO());
                model.addAttribute("message", "The interviewer that you want update does not exist.");
            }
            else{
                model.addAttribute("interviewerDTO", interviewerDb.get());
                model.addAttribute("message", "Edit interviewer " +  interviewerDb.get().getName() + " "+ interviewerDb.get().getLast_Name() + " ");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return home+form;
    }

    @RequestMapping(value={"/**"}, method={RequestMethod.GET, RequestMethod.POST})
    public String allRequests(Model model ,RedirectAttributes redirectAttributes) {

        model.addAttribute("interviewerDTO", new InterviewerDTO());
        model.addAttribute("listaPersonas", interviewerService.findAllInterviewers());

        return home+index;
    }

}