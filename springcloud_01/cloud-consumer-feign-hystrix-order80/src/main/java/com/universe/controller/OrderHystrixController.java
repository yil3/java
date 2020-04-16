package com.universe.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.universe.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class OrderHystrixController {

  @Resource
  private PaymentHystrixService paymentHystrixService;

  @GetMapping("/consumer/payment/paymentOK")
  public String paymentOK() {
    return paymentHystrixService.paymentOK();
  }

//  @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod", commandProperties = {
//          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//          value = "2000")})
//  @HystrixCommand
  @GetMapping("/consumer/payment/paymentTimeout")
  public String paymentTimeout() {
//    int i = 10/0;
    return paymentHystrixService.paymentTimeout();
  }

  public String paymentTimeoutFallbackMethod() {
    return "自定义设置80页面繁忙!!!";
  }

  public String defaultFallback () {
    return "全局设置80页面繁忙!!!";
  }
}
