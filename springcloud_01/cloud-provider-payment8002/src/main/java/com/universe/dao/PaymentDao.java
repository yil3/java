package com.universe.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao extends BaseMapper<Payment> {
}
