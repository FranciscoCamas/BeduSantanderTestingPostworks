package org.bedu.testing.controllers.web;

import org.bedu.testing.models.CandidateDTO;
import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.services.imp.CandidateService;
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
@RequestMapping("candidates")
public class CandidateWebController {

    private final String home="candidates/";
    private final String index="index";
    private final String form="candidate-form";
    private final String myDTO="candidateDTO";
    private final String myLis="listaPersonas";
    private final String myMsg="message";
    @Autowired
    private final CandidateService candidateService;

    public CandidateWebController(CandidateService candidateService) {

        this.candidateService = candidateService;
    }

    @GetMapping({"","/","/index/","/update","/registro","/edit"})
    public String CandidateWebController(Model model, @Param("keyword") String keyword) {

        if(keyword== null)
            model.addAttribute(myLis, candidateService.findAllCandidates());
        else
            model.addAttribute(myLis, candidateService.findByNameOrEmail(keyword));

        return home +index;
    }

    @GetMapping("/candidate-form")
    public String AddTutorial( Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute(myDTO, new CandidateDTO());
        model.addAttribute(myMsg, "Adding a new candidate " + " ");

        return home+form;
    }

    @PostMapping("/registro")
    public ModelAndView registra(@Valid CandidateDTO candidateDTO , BindingResult result, Model model) {

        ModelAndView mav = new ModelAndView(home +index);

        try {
            if (!result.hasErrors()){
                this.candidateService.saveCandidate (candidateDTO);
                mav.addObject(myMsg, "The candidate " + candidateDTO.getName() + candidateDTO.getLast_Name() + " has been added successfully!");
            }

        } catch (Exception e) {
            mav.addObject(myMsg, e.getMessage());
        }
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update( @Valid  CandidateDTO candidateDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView(home +form);

        try {

            if (candidateDTO !=null){
                model.addAttribute(myDTO, candidateDTO);

                if ( !result.hasErrors() ){
                    if ( candidateDTO.getId()!= null )
                        model.addAttribute(myMsg, "The candidate " + candidateDTO.getName() + candidateDTO.getLast_Name() + " has been updated successfully!");
                    else
                        model.addAttribute(myMsg, "The candidate " + candidateDTO.getName() + candidateDTO.getLast_Name() + " has been added successfully!");

                    this.candidateService.saveCandidate (candidateDTO);
                }

                return mav;
            }

            model.addAttribute(myDTO, new CandidateDTO() );
            model.addAttribute(myMsg, "The candidate that you want update does not exist.");

        } catch (Exception e) {
            model.addAttribute(myMsg, e.getMessage());
        }

        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<CandidateDTO> candidateDb = this.candidateService.findCandidateById (id);

            if (candidateDb.isEmpty())
                redirectAttributes.addFlashAttribute(myMsg, "The candidate that you want delete does not exist.");
            else{
                candidateService.delete (id);
                redirectAttributes.addFlashAttribute(myMsg, "The candidate " + candidateDb.get().getName() + candidateDb.get().getLast_Name() + " has been deleted successfully!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }

        return "redirect:/"+home;
    }

    @GetMapping("/edit/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            Optional<CandidateDTO> candidateDb = candidateService.findCandidateById (id);

            if (candidateDb.isEmpty()){
                model.addAttribute(myDTO, new InterviewerDTO());
                model.addAttribute(myMsg, "The candidate that you want update does not exist.");
            }
            else{
                model.addAttribute(myDTO, candidateDb.get());
                model.addAttribute(myMsg, "Edit candidate " +  candidateDb.get().getName() + " "+ candidateDb.get().getLast_Name() + " ");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }
        return home+form;
    }

    @RequestMapping(value={"/**"}, method={RequestMethod.GET, RequestMethod.POST})
    public String allRequests(Model model ,RedirectAttributes redirectAttributes) {

        model.addAttribute(myDTO, new CandidateDTO());
        model.addAttribute(myLis, candidateService.findAllCandidates ());

        return home+index;
    }

}