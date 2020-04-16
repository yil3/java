package com.universe.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class PaymentController {

  @Value("${server.port}")
  private String serverPort;

  @RequestMapping("/payment/zk")
  public String paymentZk(){
    return "springCloud with zk!!!Port:"+serverPort+"\t"+ UUID.randomUUID().toString();
  }

}
