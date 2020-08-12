package com.xuecheng.manage_cms;

import com.xuecheng.manage_cms.service.CmsPageServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageServiceTest {

    @Resource
    CmsPageServiceImpl cmsPageServiceImpl;

    @Test
    public void getPageHtml(){
        String pageHtml = cmsPageServiceImpl.getPageHtml("5ed3ec5102b9d218e4b00044");
        System.out.println(pageHtml);
    }
}
