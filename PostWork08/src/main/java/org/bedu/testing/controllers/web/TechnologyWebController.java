package org.bedu.testing.controllers.web;

import org.bedu.testing.models.CandidateDTO;
import org.bedu.testing.models.DisciplineDTO;
import org.bedu.testing.models.InterviewerDTO;
import org.bedu.testing.models.TechnologyDTO;
import org.bedu.testing.services.imp.DisciplineService;
import org.bedu.testing.services.imp.TechnologyService;
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
@RequestMapping("technologies")
public class TechnologyWebController {

    private final String home="technologies/";
    private final String index="index";
    private final String form="technology-form";
    private final String myDTO="technologyDTO";
    private final String myLis="listaPersonas";
    private final String myMsg="message";
    @Autowired
    private final TechnologyService technologyService ;

    public TechnologyWebController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping({"","/","/index/","/update","/registro","/edit"})
    public String technologygetHome(Model model, @Param("keyword") String keyword) {

        if(keyword== null)
            model.addAttribute(myLis, technologyService.findAllTechnologies ());
        else
            model.addAttribute(myLis, technologyService.findByNamerDescriptionSlug (keyword));

        return home +index;
    }

    @GetMapping("/technology-form")
    public String addTechnology( Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute(myDTO, new TechnologyDTO());
        model.addAttribute(myMsg, "Adding a new Technology " + " ");

        return home+form;
    }

    @PostMapping("/registro")
    public ModelAndView registra(@Valid TechnologyDTO technologyDTO , BindingResult result, Model model) {

        ModelAndView mav = new ModelAndView(home +index);

        try {
              if (!result.hasErrors()){
                this.technologyService.saveTechnology (technologyDTO);
                mav.addObject(myMsg, "The technology " + technologyDTO.getName() + " has been added successfully!");
             }
        } catch (Exception e) {
            mav.addObject(myMsg, e.getMessage());
        }
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView update( @Valid  TechnologyDTO technologyDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView(home +form);

        try {

            if (technologyDTO !=null){
                model.addAttribute(myDTO, technologyDTO);

                if ( !result.hasErrors() ){

                    if ( technologyDTO.getId()!= null )
                        model.addAttribute(myMsg, "The technology " + technologyDTO.getName()  + " has been updated successfully!");
                    else
                        model.addAttribute(myMsg, "The technology " + technologyDTO.getName()  + " has been added successfully!");

                    this.technologyService.saveTechnology (technologyDTO);
                }

                return mav;
            }

            model.addAttribute(myDTO, new TechnologyDTO() );
            model.addAttribute(myMsg, "The technology that you want update does not exist.");

        } catch (Exception e) {
            model.addAttribute(myMsg, e.getMessage());
        }

        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<TechnologyDTO> technologyDb = this.technologyService.findTechnologyById (id);

            if (technologyDb.isEmpty())
                redirectAttributes.addFlashAttribute(myMsg, "The technology that you want delete does not exist.");
            else{
                technologyService.delete (id);
                redirectAttributes.addFlashAttribute(myMsg, "The technology " + technologyDb.get().getName() + " has been deleted successfully!");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }

        return "redirect:/"+home;
    }

    @GetMapping("/edit/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {

            Optional<TechnologyDTO> technologyDb = technologyService.findTechnologyById (id);

            if (technologyDb.isEmpty()){
                model.addAttribute(myDTO, new InterviewerDTO());
                model.addAttribute(myMsg, "The technology that you want update does not exist.");
            }
            else{
                model.addAttribute(myDTO, technologyDb.get());
                model.addAttribute(myMsg, "Edit technology " +  technologyDb.get().getName() + " ");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(myMsg, e.getMessage());
        }
        return home+form;
    }

    @RequestMapping(value={"/**"}, method={RequestMethod.GET, RequestMethod.POST})
    public String allRequests(Model model ,RedirectAttributes redirectAttributes) {

        //model.addAttribute(myDTO, new DisciplineDTO());
        model.addAttribute(myLis, technologyService.findAllTechnologies ());

        return home+index;
    }

}