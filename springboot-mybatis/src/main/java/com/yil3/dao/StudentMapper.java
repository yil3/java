package com.yil3.dao;


import com.yil3.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  //表示这是一个mybatis的mapper类
@Repository  //这个类被ioC容器接管
public interface StudentMapper {
    public List<Student> findAll();

    public Student findById(int id);

    public int save(Student student);

    public int update (Student student);

    public int deleteById(int id);
}
