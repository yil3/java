package com.universe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZkController {

  public final String PAYMENT_URL = "http://cloud-payment-service";

  @Resource
  private RestTemplate restTemplate;

  @GetMapping("/consumer/payment/zk")
  public String paymentInfo(){
    return restTemplate.getForObject(PAYMENT_URL+"/payment/zk", String.class);
  }
}
