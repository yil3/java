package com.ihrm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihrm.entity.system.PermissionApi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PermissionApiDao extends BaseMapper<PermissionApi> {
}
