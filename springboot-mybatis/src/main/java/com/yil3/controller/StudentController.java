package com.yil3.controller;


import com.yil3.dao.StudentMapper;
import com.yil3.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;


    @GetMapping("/findAll")
    @ResponseBody
    public List<Student> findAll(){
        return studentMapper.findAll();
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public Student findById(@PathVariable int id){
        return studentMapper.findById(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public Student save(@RequestBody Student student){
        studentMapper.save(student);
//        Student s = new Student(10,"各位",33);
        return student;
    }

    @GetMapping("/toSave")
    public String toSave(){
        return "toSave";
    }
}
