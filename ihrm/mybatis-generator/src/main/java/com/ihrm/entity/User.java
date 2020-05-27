package com.ihrm.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * bs_user
 * @author x
 * @date 2020-05-23 08:03:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    /**
     * ID
     * 字段 : id
     */
    private Long id;

    /**
     * 手机号码
     * 字段 : mobile
     */
    private String mobile;

    /**
     * 用户名称
     * 字段 : username
     */
    private String username;

    /**
     * 密码
     * 字段 : password
     */
    private String password;

    /**
     * 启用状态 0是禁用，1是启用
     * 字段 : enable_state
     */
    private Integer enableState;

    /**
     * 创建时间
     * 字段 : create_time
     */
    private Date createTime;

    /**
     * 部门ID
     * 字段 : department_id
     */
    private String departmentId;

    /**
     * 入职时间
     * 字段 : time_of_entry
     */
    private Date timeOfEntry;

    /**
     * 聘用形式
     * 字段 : form_of_employment
     */
    private Integer formOfEmployment;

    /**
     * 工号
     * 字段 : work_number
     */
    private String workNumber;

    /**
     * 管理形式
     * 字段 : form_of_management
     */
    private String formOfManagement;

    /**
     * 工作城市
     * 字段 : working_city
     */
    private String workingCity;

    /**
     * 转正时间
     * 字段 : correction_time
     */
    private Date correctionTime;

    /**
     * 在职状态 1.在职  2.离职
     * 字段 : in_service_status
     */
    private Integer inServiceStatus;

    /**
     * 企业ID
     * 字段 : company_id
     */
    private Long companyId;

    /**
     * 
     * 字段 : company_name
     */
    private String companyName;

    /**
     * 
     * 字段 : department_name
     */
    private String departmentName;

    /**
     * 用户级别：saasAdmin，coAdmin，user
     * 字段 : level
     */
    private String level;

    /**
     * 离职时间
     * 字段 : time_of_dimission
     */
    private Date timeOfDimission;

    /**
     * 
     * 字段 : staff_photo
     */
    private String staffPhoto;

    private static final long serialVersionUID = 1L;
}