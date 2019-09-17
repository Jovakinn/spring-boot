package com.mainacad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/index", "/main"})
    public String getLandingPage() {
        return "index";
    }
}