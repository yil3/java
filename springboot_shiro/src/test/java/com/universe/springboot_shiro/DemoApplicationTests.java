package com.universe.springboot_shiro;

import com.universe.entity.User;
import com.universe.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		User user = userService.findByUserName("root");
		System.out.println(user);
	}

}
