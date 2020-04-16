package com.universe.controller;

import com.universe.entity.WUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

  @GetMapping("login")
  public String toLogin(){
    return "login";
  }

  @PostMapping("login")
  public String login(WUser wUser){
    if(!wUser.getUsername().isEmpty()){
      return "redirect:index";
    }
    return "login";
  }

  @GetMapping("hello")
  @ResponseBody
  public String hello(){
    return "HelloWorld!";
  }


}