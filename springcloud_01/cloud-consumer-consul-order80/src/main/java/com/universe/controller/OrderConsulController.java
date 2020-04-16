package com.universe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {

  public final String INVOKE_URL = "http://consul-provider-payment";
  @Resource
  private RestTemplate restTemplate;

  @GetMapping("/consumer/payment/consul")
  public String consulInfo(){
    return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
  }
}
