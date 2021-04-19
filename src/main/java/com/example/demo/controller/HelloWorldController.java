package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HelloWorldController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!!");
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String index(@RequestParam("files") List<MultipartFile> files, Model model) {
        for (MultipartFile file : files) {
            System.out.println("getOriginalFilename="+file.getOriginalFilename());
        }
        return "index";
    }
}