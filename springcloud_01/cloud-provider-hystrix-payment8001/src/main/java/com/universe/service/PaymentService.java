package com.universe.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

  public String paymentOK(){
    return "---线程---:"+ Thread.currentThread().getName() + "-----paymentOK-----" + UUID.randomUUID().toString();
  }


  @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {@HystrixProperty(name = "execution" +
          ".isolation.thread.timeoutInMilliseconds",value = "3000")})
  public String paymentTimeout(){

    try {
      TimeUnit.MILLISECONDS.sleep(2950);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
//    int a = 10/0;
    return "---线程---:"+ Thread.currentThread().getName() + "-----paymentTimeout-----" + UUID.randomUUID().toString();
  }

  public String paymentTimeoutHandler(){
    return "---线程---:"+ Thread.currentThread().getName() + "-----error:繁忙-----" + UUID.randomUUID().toString();
  }
}
