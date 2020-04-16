package com.yil3.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Student2 {
    private int id;
    private  String name;
    private  int age;
    private List<Address> addresses;
}
