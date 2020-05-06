package com.universe;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TestRabbitMQ {

  @Resource
  private RabbitTemplate rabbitTemplate;

  @Test
  void topic(){
    rabbitTemplate.convertAndSend("topics", "user.find", "topic模型发布消息");
  }

  @Test
  void route(){
    for (int i = 0; i < 10; i++) {
      rabbitTemplate.convertAndSend("directs","error","route模型发布消息" + i);
    }
  }

  @Test
  void  fanout(){
    for (int i = 0; i < 10; i++) {
      rabbitTemplate.convertAndSend("logs","","Fanout模型发布的消息" + i);
    }
  }

  @Test
  void  work(){
    for (int i = 0; i < 10; i++) {
      rabbitTemplate.convertAndSend("work","work模型");
    }
  }
  @Test
  void demo01() {
    rabbitTemplate.convertAndSend("hello","HelloWorld!!");
  }

}
