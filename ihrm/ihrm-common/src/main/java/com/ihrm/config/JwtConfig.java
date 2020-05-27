package com.ihrm.config;

import com.ihrm.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

  @Bean
  public JwtUtils jwtUtils (){
    return new JwtUtils();
  }
}
