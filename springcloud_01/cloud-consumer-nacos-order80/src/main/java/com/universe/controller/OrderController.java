package com.universe.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

  @Resource
  private RestTemplate restTemplate;
  @Value("${server.url}")
  private String serverURL;

//  private final String SERVER_URL = "http://nacos-provider-payment";

  @GetMapping("/consumer/payment/nacos")
  public String paymentInfo(){
    return restTemplate.getForObject(serverURL+"/payment/nacos",String.class);
  }
}


