package com.ihrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihrm.entity.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {

}
