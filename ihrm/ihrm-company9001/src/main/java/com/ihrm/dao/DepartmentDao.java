package com.ihrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihrm.entity.company.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentDao extends BaseMapper<Department> {
}
