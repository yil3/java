package com.xuecheng.manage_cms.service;

import com.xuecheng.service.cms.SysDictionaryService;
import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.dao.SysDictionaryDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {
    @Resource
    SysDictionaryDao sysDictionaryDao;
    @Override
    public SysDictionary findByType(String type) {
        return sysDictionaryDao.findBydType(type);
    }
}
