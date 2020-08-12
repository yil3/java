package com.xuecheng.manage_cms.controller;

import com.xuecheng.service.cms.SysDictionaryService;
import com.xuecheng.framework.domain.system.SysDictionary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController {
    @Resource
    SysDictionaryService sysDictionaryService;

    @GetMapping("/get/{type}")
    public SysDictionary findByType (@PathVariable("type") String type){
        return sysDictionaryService.findByType(type);
    }
}
