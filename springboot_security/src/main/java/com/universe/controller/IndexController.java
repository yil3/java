package com.universe.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class IndexController {
  
  @GetMapping("index")
  public String index(){
    return "index";
  }

  @GetMapping("lv01/{id}")
  public String lv01(@PathVariable int id){
    return "lv01/"+id;
  }

  @GetMapping("lv02/{id}")
  public String lv02(@PathVariable int id){
    return "lv02/"+id;
  }

  @GetMapping("lv03/{id}")
  public String lv03(@PathVariable int id){
    return "lv03/"+id;
  }

}