package com.universe.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  @LoadBalanced  //启动负载均衡
  public RestTemplate getRestTemplate(){
    return new RestTemplate();
  }
}
