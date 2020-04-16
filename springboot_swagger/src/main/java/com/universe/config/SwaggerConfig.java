package com.universe.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2  // 开启Swagger
public class SwaggerConfig {

  @Bean
  public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2)
    .apiInfo(apiInfo())
    // 配置组名
    .groupName("肖")
    // 是否启动Swagger
    // .enable(false)
    .select()
    // 指定扫描包
    .apis(RequestHandlerSelectors.basePackage("com.universe"))
    .build();
  }

  public ApiInfo apiInfo(){
    return new ApiInfo("Api文档", null, null, null, null, null, null, new ArrayList<>());
  }

}