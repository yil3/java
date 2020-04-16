package com.universe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends BaseMapper<User> {
}
