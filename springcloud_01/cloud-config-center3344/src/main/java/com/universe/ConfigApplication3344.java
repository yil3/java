package com.universe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication3344 {
  public static void main(String[] args) {
    SpringApplication.run(ConfigApplication3344.class,args);
  }
}
