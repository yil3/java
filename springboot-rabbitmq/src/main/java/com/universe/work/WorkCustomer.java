package com.universe.work;


import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkCustomer {

  @RabbitListener(queuesToDeclare = @Queue("work"))
  public void work01(String message){
    System.out.println("message1: " + message);
  }

  @RabbitListener(queuesToDeclare = @Queue("work"))
  public void work02(String message){
    System.out.println("message2: " + message);
  }
}
