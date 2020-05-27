package com.ihrm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bs_user")
public class User implements Serializable {

  @Id
  private long id;
  /**
   * 手机号码
   */
//    @ExcelAttribute(sort = 2)
  private String mobile;
  /**
   * 用户名称
   */
//    @ExcelAttribute(sort = 1)
  private String username;
  /**
   * 密码
   */
  private String password;

  /**
   * 启用状态 0为禁用 1为启用
   */
  private Integer enableState;
  /**
   * 创建时间
   */
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  private String companyId;

  private String companyName;

  /**
   * 部门ID
   */
//    @ExcelAttribute(sort = 6)
  private String departmentId;

  /**
   * 入职时间
   */
//    @ExcelAttribute(sort = 5)
  private Date timeOfEntry;

  /**
   * 聘用形式
   */
//    @ExcelAttribute(sort = 4)
  private Integer formOfEmployment;

  /**
   * 工号
   */
//    @ExcelAttribute(sort = 3)
  private String workNumber;

  /**
   * 管理形式
   */
  private String formOfManagement;

  /**
   * 工作城市
   */
  private String workingCity;

  /**
   * 转正时间
   */
  private Date correctionTime;

  /**
   * 在职状态 1.在职  2.离职
   */
  private Integer inServiceStatus;

  private String departmentName;

  /**
   * level
   *     String
   *          saasAdmin：saas管理员具备所有权限
   *          coAdmin：企业管理（创建租户企业的时候添加）
   *          user：普通用户（需要分配角色）
   */
  private String level;

  private String staffPhoto;  //用户头像

  // @ManyToMany(targetEntity = Role.class)
  // @JoinTable(name="pe_user_role",
  //     joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
  //         inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
  // private Set<Role> roles = new HashSet<>();
}
