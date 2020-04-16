package com.universe.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
  @Override
  public String paymentOK() {
    return "PaymentFallbackService------paymentOK-----繁忙！！！";
  }

  @Override
  public String paymentTimeout() {
    return "PaymentFallbackService------paymentTimeout-----繁忙！！！";
  }
}
