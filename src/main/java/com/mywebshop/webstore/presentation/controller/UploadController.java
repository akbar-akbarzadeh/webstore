package com.mywebshop.webstore.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @GetMapping("/showform")
    public String uploadController1() {

        return "uploadpage";

    }

    @PostMapping("/processform")
    public String uploadController(MultipartFile file) {

        System.out.println(file.getOriginalFilename());

        return "uploadpage";


    }
}
