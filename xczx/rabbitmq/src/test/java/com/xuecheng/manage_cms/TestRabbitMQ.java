package com.xuecheng.manage_cms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRabbitMQ {
    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("xxxx", "aaaa", "ttttt----dasd----dsadas");
        }
    }
}
