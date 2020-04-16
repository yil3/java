package com.yil3.controller;


import com.yil3.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello (Model model){
        model.addAttribute("msg","HelloSpringMVC!");
        return "hello";
    }

    @PostMapping("/save")
    public String save(User user){
        System.out.println(user);
        return "redirect:hello";
    }
}
