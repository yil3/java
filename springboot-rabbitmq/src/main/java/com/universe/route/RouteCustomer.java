package com.universe.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteCustomer {

  @RabbitListener(bindings = {
      @QueueBinding(
          value = @Queue, //创建临时队列
          exchange = @Exchange(value = "directs", type = "direct"), //绑定交换机
          key = {"info", "warn" ,"error"}  //可接受级别
      )
  })
  public void route01(String message){
    System.out.println("message1 : " + message);
  }

  @RabbitListener(bindings = {
      @QueueBinding(
          value = @Queue, //创建临时队列
          exchange = @Exchange(value = "directs", type = "direct"),
          key = {"error"}
      )
  })
  public void route02(String message){
    System.out.println("message2 : " + message);
  }
}
