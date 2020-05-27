package com.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pe_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;

    @JsonSerialize(using = ToStringSerializer.class)
    @Id
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

//    @JsonIgnore
//    @ManyToMany(targetEntity = User.class, mappedBy="roles")  //不维护中间表
//    private Set<User> users = new HashSet<User>(0);//角色与用户   多对多
//
//
//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(name="pe_role_permission",
//            joinColumns={@JoinColumn(name="role_id",referencedColumnName="id")},
//            inverseJoinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")})
//    private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多
}