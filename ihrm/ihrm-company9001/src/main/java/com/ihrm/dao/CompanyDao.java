package com.ihrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihrm.entity.company.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyDao extends BaseMapper<Company> {
}
