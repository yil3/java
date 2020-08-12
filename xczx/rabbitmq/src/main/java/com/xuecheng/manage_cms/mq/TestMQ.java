package com.xuecheng.manage_cms.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestMQ {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("tttt"),
                    exchange = @Exchange(value = "xxxx",type = "topic"),
                    key = {"aaaa"}
            )
    })
    public void test(String msg){
        System.out.println("----------------msg:" + msg + "----------------");
    }
}
