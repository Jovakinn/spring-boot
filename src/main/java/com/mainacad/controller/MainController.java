package com.mainacad.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Profile("json")
public class MainController {

    @GetMapping("/")
    public String getLandingPage() {
        return "/webapp/authorization.jsp";
    }
}