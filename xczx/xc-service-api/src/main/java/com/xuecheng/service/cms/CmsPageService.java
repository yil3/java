package com.xuecheng.service.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.Result;


public interface CmsPageService {

    Result findList(int page, int size, QueryPageRequest queryPageRequest);

    Result save(CmsPage cmsPage);

    CmsPage findById(String id);

    Result update(String id, CmsPage cmsPage);

    Result delete(String id);

    Result post(String pageId);

    Result addAndUpdate(CmsPage cmsPage);

    Result postPageQuick(CmsPage cmsPage);
}
