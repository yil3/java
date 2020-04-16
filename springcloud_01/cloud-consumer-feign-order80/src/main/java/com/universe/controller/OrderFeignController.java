package com.universe.controller;

import com.universe.entity.CommonResult;
import com.universe.entity.Payment;
import com.universe.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

  @Resource
  private PaymentFeignService paymentFeignService;

  @GetMapping("/consumer/payment/findAll")
  public CommonResult<Payment> findAll(){
    CommonResult result = paymentFeignService.findAll();
    return result;
  }

  @GetMapping("/consumer/payment/timeOut")
  public String timeOut(){
    return paymentFeignService.timeOut();
  }
}