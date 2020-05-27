package com.ihrm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * MybatisPlus
 * 初始化值
 * */
import java.util.Date;

//加上Component交由Spring管理
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

  //使用mp实现添加操作，这个方法执行  createTime为字段  new Date()为现在时间  metaObject
  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createTime",new Date(),metaObject);
    this.setFieldValByName("updateTime",new Date(),metaObject);

    this.setFieldValByName("version",1,metaObject);
  }

  //使用mp实现修改操作，这个方法执行
  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateTime",new Date(),metaObject);
  }
}