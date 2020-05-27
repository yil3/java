package com.ihrm.User.entity;

import 你自己的父类实体,没有就不用设置!;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author x
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BsUser extends 你自己的父类实体,没有就不用设置! {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 启用状态 0是禁用，1是启用
     */
    private Integer enableState;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 入职时间
     */
    private LocalDateTime timeOfEntry;

    /**
     * 聘用形式
     */
    private Integer formOfEmployment;

    /**
     * 工号
     */
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
    private LocalDateTime correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     */
    private Integer inServiceStatus;

    /**
     * 企业ID
     */
    private Long companyId;

    private String companyName;

    private String departmentName;

    /**
     * 用户级别：saasAdmin，coAdmin，user
     */
    private String level;

    private String staffPhoto;

    /**
     * 离职时间
     */
    private LocalDateTime timeOfDimission;


}
