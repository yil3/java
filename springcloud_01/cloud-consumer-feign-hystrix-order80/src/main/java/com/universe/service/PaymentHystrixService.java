package com.universe.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "cloud-provider-Hystrix-payment", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

  @GetMapping("/payment/paymentOK")
  String paymentOK();

  @GetMapping("/payment/paymentTimeout")
  String paymentTimeout();
}
