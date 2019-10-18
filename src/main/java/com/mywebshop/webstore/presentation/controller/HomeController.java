package com.mywebshop.webstore.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String showHomePage(Model model) {

        model.addAttribute("greeting", " Welcome to Web Store!");
        model.addAttribute("tagline", " the only and one webstore");

        return "welcome";

    }
}
