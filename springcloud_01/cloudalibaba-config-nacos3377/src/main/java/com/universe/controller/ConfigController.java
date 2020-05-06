package com.universe.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //nacos动态刷新
public class ConfigController {

  @Value("${config.info}")
  private String configInfo;


  @GetMapping("/config/info")
  public String configInfo(){
    return configInfo;
  }
}
