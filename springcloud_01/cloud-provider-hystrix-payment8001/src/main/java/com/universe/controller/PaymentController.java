package com.universe.controller;

import com.universe.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {

  @Resource
  private PaymentService paymentService;

  @GetMapping("/payment/paymentOK")
  public String paymentOK(){
    return paymentService.paymentOK();
  }

  @GetMapping("/payment/paymentTimeout")
  public String paymentTimeout(){
    return paymentService.paymentTimeout();
  }
}
