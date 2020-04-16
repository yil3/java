package com.universe;



import com.fasterxml.jackson.core.JsonProcessingException;

import com.universe.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;





@SpringBootTest
class DemoApplicationTests {



	@Autowired
	private RedisTemplate<String, Object> redisTemplate;



	@Test
	void contextLoads() throws JsonProcessingException {
		User user = new User("小",20);
		redisTemplate.opsForValue().set("user", user);
		System.out.println(redisTemplate.opsForValue().get("user"));

	}

}
