package com.universe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.universe.dao")
public class SpringbootUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUploadApplication.class, args);
    }

}
