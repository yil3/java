package com.universe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.entity.FUser;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FUserDao extends BaseMapper<FUser>{

}