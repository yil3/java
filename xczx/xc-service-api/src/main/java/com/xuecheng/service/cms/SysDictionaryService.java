package com.xuecheng.service.cms;

import com.xuecheng.framework.domain.system.SysDictionary;

public interface SysDictionaryService {
    SysDictionary findByType(String type);
}
