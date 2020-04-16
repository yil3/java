package com.universe.controller;

import com.universe.entity.User;
import com.universe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public String login(User user, HttpSession httpSession){
        User userDB = userService.login(user);
        if (userDB != null){
            httpSession.setAttribute("user",userDB);
            return "redirect:/showAll";
        }else {
            return "redirect:/login";
        }
    }
}
