package com.xuecheng.manage_cms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRabbitMq {
    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    public void  testRabbit(){
        rabbitTemplate.convertAndSend("ex_routing_cms_postpage",
                "5a751fab6abb5044e0d19ea1","rabbitTest,测试测试！！！");
    }
}
