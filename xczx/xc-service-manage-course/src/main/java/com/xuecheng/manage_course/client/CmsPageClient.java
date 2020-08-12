package com.xuecheng.manage_course.client;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.model.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("XC-SERVICE-MANAGE-CMS")
public interface CmsPageClient {

    @PostMapping("/cms/page/addAndUpdate")
    Result addAndUpdate(@RequestBody CmsPage cmsPage);

    @PostMapping("/cms/page/postPageQuick")
    Result postPageQuick(@RequestBody CmsPage cmsPage);

}
