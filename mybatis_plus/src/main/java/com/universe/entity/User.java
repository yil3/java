package com.universe.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mybatis_plus")
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;
    private Integer age;
    private String email;
}
