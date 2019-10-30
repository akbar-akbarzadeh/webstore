package com.mywebshop.webstore.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping() //default request mapping
    public String showHomePage(Model model) {

        model.addAttribute("greeting", " Welcome to Web Store!");
        model.addAttribute("tagline", " the only and one webstore");

        return "welcome";

    }


}
