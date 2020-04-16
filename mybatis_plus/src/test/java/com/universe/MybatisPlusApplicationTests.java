package com.universe;

import com.universe.dao.UserDAO;
import com.universe.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserDAO userDAO;
    @Test
    void contextLoads() {
        List<User> users = userDAO.selectList(null);
        users.forEach(System.out::println);
        System.out.println(userDAO);
    }

    @Test
    void inset(){
        User user = new User();
        user.setAge(20);
        user.setName("universe");
        user.setEmail("123@123.sina.com");
        int res = userDAO.insert(user);
        System.out.println(res);
        System.out.println(user);
    }


}
