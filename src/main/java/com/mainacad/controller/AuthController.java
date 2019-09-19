package com.mainacad.controller;

import com.mainacad.entity.User;
import com.mainacad.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/authorization")
@Profile("json")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping()
    public String getAuthUser(Model model,
                              @RequestParam(value="login") String login,
                              @RequestParam(value="pass") String password) {

        User user = userService.findOneByLoginAndPassword(login, password);
        if (user != null){
            model.addAttribute("name", user.getFirstName());
            model.addAttribute("surname", user.getLastName());

            return "good-login";
        }

        return "wrong-login";
    }
}
