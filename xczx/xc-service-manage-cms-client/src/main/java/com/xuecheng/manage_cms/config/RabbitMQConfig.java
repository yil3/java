package com.xuecheng.manage_cms.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "queue_cms_postpage_01";
    public static final String ROUTING_KEY = "5a751fab6abb5044e0d19ea1";
    public static final String EXCHANGE = "ex_routing_cms_postpage";

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public Binding binding(@Qualifier("queue") Queue queue,
                           @Qualifier("exchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }
}
