package com.yil3.controller;

import com.yil3.pojo.Address;
import com.yil3.pojo.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonController {

    @RequestMapping("/json")
    @ResponseBody
    public User json(@RequestBody User user){
        System.out.println(user);

        Address address = new Address();
        address.setName("深圳");

        user.setId(20);
        user.setName("表分");
        user.setAddress(address);

        return user;
    }


    @RequestMapping("/model")
    public String model (Map<String,User> map){
        User user = new User();
        user.setId(10);
        user.setName("西安市");
        map.put("user",user);
        return "hello";

    }

    @RequestMapping("/session")
    public  String session(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("msg1","hello~~Session");
        return "hello";
    }



}
