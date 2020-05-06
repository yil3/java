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
  void demo01() {
    rabbitTemplate.convertAndSend("hello","HelloWorld!!");
  }

}
