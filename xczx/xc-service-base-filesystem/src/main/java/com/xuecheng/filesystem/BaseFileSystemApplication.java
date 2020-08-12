package com.xuecheng.filesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.filesystem")
@ComponentScan("com.xuecheng.framework")
@ComponentScan("com.xuecheng.service")
public class BaseFileSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseFileSystemApplication.class,args);
    }
}
