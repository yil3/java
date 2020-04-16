package com.universe.service;

import com.universe.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

  @GetMapping("/payment/findAll")
  CommonResult findAll();

  @GetMapping("/payment/timeOut")
  String timeOut();
}