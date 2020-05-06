package com.universe.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

  @Value("${server.port}")
  private String port;

  @GetMapping("/payment/nacos")
  public String getPayment (){
    return "nacos----->port: " + port + "---------UUID------->" + UUID.randomUUID().toString();
  }
}
