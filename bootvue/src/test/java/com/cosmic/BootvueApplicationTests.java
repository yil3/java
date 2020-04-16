package com.cosmic;

import com.cosmic.dao.UserDao;
import com.cosmic.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BootvueApplicationTests {

    @Autowired
    private UserDao userDao;
    @Test
    public void test(){
        List<User> users = userDao.findAll();
        System.out.println(users);
    }

    @Test
    public void test1(){
        List<User> user = userDao.findByName("1");
        if (user.size()>0){
            System.out.println(user);
        }else {
            System.out.println("no item!");
        }
    }

    @Test
    public void test2(){
        User user = userDao.findById(1);
        System.out.println(user);
    }

}
