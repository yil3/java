package com.xuecheng.manage_cms.mq;


import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.config.RabbitMQConfig;
import com.xuecheng.manage_cms.service.PageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Log4j2
@Component
public class ConsumerPostPage {
    @Resource
    PageService pageService;
    public static final String EXCHANGE = "ex_routing_cms_postpage";

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("queue_cms_postpage_03"),
                    exchange = @Exchange(value = EXCHANGE, type = "topic"),
                    key = {"5ede5b2cd2c2e2160e7dcca9"}
            )
    })
    public void postPageQuick(String msg){
        Map map = JSON.parseObject(msg, Map.class);
        String pageId = (String) map.get("pageId");
        CmsPage cmsPage = pageService.findCmsPageById(pageId);
        if (cmsPage == null) {
            log.error("postPage msg: CmsPage is null，id：{}",pageId);
            return;
        }
        // 将页面从gridFs下载到服务器本地
        pageService.savePageToServerPath(pageId);
    }

    @RabbitListener(queues = {RabbitMQConfig.QUEUE})
    public void postPage(String msg){
        Map map = JSON.parseObject(msg, Map.class);
        String pageId = (String) map.get("pageId");
        CmsPage cmsPage = pageService.findCmsPageById(pageId);
        if (cmsPage == null) {
            log.error("postPage msg: CmsPage is null，id：{}",pageId);
            return;
        }
        // 将页面从gridFs下载到服务器本地
        pageService.savePageToServerPath(pageId);
    }
}
