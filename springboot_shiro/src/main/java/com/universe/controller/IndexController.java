package com.universe.controller;




import com.universe.entity.FUser;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

  @GetMapping("/")
  public String index( Model model){
    model.addAttribute("msg","Hello Shiro!");
    return "index";
  }

  @GetMapping("/add")
  public String add( Model model){
    return "add";
  }

  @GetMapping("/update")
  public String update( Model model){
    return "update";
  }

  @PostMapping("/login")
  public String login(FUser fUser, Model model){
    // 获取用户
    Subject subject = SecurityUtils.getSubject();
    // 封装登录数据
    UsernamePasswordToken token = new UsernamePasswordToken(fUser.getUsername(),fUser.getPassword());

    try {
      subject.login(token);
      return "redirect:/";
    } catch (UnknownAccountException e) {
      model.addAttribute("code", "用户名不存在！");
      return "login";
    } catch (IncorrectCredentialsException e){
      model.addAttribute("code", "密码错误！");
      return "login";
    }
  }
  
  @GetMapping("/login")
  public String tologin(){
    return "login";
  }

  @GetMapping("/unauthorized")
  @ResponseBody
  public String unauthorized (){
    return "无权限访问";
  }

  @GetMapping("/logout")
  public String logout(){
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return "redirect:/";
  }
}