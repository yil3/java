package com.universe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentCloudAlibabaApplication9001 {
  public static void main(String[] args) {
    SpringApplication.run(PaymentCloudAlibabaApplication9001.class,args);
  }
}
