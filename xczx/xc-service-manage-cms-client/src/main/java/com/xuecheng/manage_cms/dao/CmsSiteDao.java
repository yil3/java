package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.mapstruct.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CmsSiteDao extends MongoRepository<CmsSite,String> {
}
