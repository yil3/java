package com.ihrm.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.dreamyoung.mprelation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@TableName("pe_role")
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;

    @TableField(exist = false)
    private Set<String> permissionIds = new HashSet<>(0);//角色与模块  多对多

//    @JsonIgnore
//    @ManyToMany(mappedBy="roles")  //不维护中间表

   // @TableField(exist = false)
   // @ManyToMany(targetEntity = User.class)
   // @JoinTable(name = "pe_user_role",
   //     joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
   //     inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
   // private Set<User> users = new HashSet<User>(0);//角色与用户   多对多
//
//
//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(name="pe_role_permission",
//            joinColumns={@JoinColumn(name="role_id",referencedColumnName="id")},
//            inverseJoinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")})
//    private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多
}