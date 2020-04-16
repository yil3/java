package com.universe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("login")
    public String toLogin(){
        return "login";
    }


}
