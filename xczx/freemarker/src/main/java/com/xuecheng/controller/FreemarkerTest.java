package com.xuecheng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class FreemarkerTest {
    @Resource
    RestTemplate restTemplate;

    @RequestMapping("/index")
    public String index(Model model){

        model.addAttribute("name","xxx");

        return "index";
    }

    @RequestMapping("/banner")
    public String banner(Model model){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getmodel/5a791725dd573c3574ee333f", Map.class);
        Map body = forEntity.getBody();
        Map<String, Object> map = model.asMap();
        map.putAll(body);
        return "index_banner";
    }

    @RequestMapping("/course")
    public String course(Model model){
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31200/course/courseview/4028e581617f945f01617f9dabc40000", Map.class);
        Map body = forEntity.getBody();
        Map<String, Object> map = model.asMap();
        map.putAll(body);
        return "course";
    }
}
