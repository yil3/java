package com.xuecheng.manage_media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain")
@ComponentScan("com.xuecheng")
public class MediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class,args);
    }
}
