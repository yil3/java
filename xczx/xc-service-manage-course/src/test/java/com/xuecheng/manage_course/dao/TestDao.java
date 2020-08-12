package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CourseBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDao {
    @Resource
    CourseBaseDao courseBaseDao;
    @Resource
    CourseBaseMapper courseBaseMapper;


    @Test
    public void testCourseBaseDao(){
        Optional<CourseBase> optional = courseBaseDao.findById("402885816240d276016240f7e5000002");
        if(optional.isPresent()){
            CourseBase courseBase = optional.get();
            System.out.println(courseBase);
        }
    }

    @Test
    public void testCourseMapper(){
        CourseBase courseBase = courseBaseMapper.findCourseBaseById("402885816240d276016240f7e5000002");
        System.out.println(courseBase);
    }
}
