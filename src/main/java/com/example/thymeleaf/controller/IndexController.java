package com.example.thymeleaf.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/students";
    }

}
