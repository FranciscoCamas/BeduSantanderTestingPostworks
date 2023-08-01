package org.bedu.testing.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class IndexController {

    @GetMapping({"/", "/index"})
    public String homePage() {

        return "index";
    }

    @GetMapping( "/contact")
    public String contactPage(Model model) {
        ModelAndView mav = new ModelAndView("contact");
        return "contact";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

}
