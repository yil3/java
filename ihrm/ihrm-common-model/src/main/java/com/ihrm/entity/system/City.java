package com.ihrm.entity.system;


import com.baomidou.mybatisplus.annotation.TableName;


import java.io.Serializable;


@TableName("bs_city")
public class City implements Serializable {


    private Long id;
    private String name;
    private java.util.Date createTime;

    public void setId(long value) {
        this.id = value;
    }
    public long getId() {
       return this.id;
    }
    public void setName(String value) {
        this.name = value;
    }
    public String getName() {
       return this.name;
    }
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    public java.util.Date getCreateTime() {
       return this.createTime;
    }
}
