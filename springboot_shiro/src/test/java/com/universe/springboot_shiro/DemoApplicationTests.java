package com.universe.springboot_shiro;

import com.universe.entity.FUser;
import com.universe.service.FUserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private FUserService FUserService;

	@Test
	void contextLoads() {
		FUser user = FUserService.selectByName("root");
		System.out.println(user);
	}

}
