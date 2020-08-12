package com.xuecheng.manage_cms.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.Result;
import com.xuecheng.manage_cms.dao.CmsConfigDao;
import com.xuecheng.manage_cms.dao.CmsPageDao;
import com.xuecheng.manage_cms.dao.CmsSiteDao;
import com.xuecheng.manage_cms.dao.CmsTemplateDao;
import com.xuecheng.service.cms.CmsConfigService;
import com.xuecheng.service.cms.CmsPageService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class CmsPageServiceImpl implements CmsPageService, CmsConfigService {

    @Resource
    CmsPageDao cmsPageDao;
    @Resource
    CmsConfigDao cmsConfigDao;
    @Resource
    CmsTemplateDao cmsTemplateDao;
    @Resource
    RestTemplate restTemplate;
    @Resource
    GridFsTemplate gridFsTemplate;
    @Resource
    GridFSBucket gridFSBucket;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    CmsSiteDao cmsSiteDao;



    @Override
    public Result findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null) queryPageRequest = new QueryPageRequest();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        //分页参数
        if (page <= 0) page = 1;
        if (size <= 0) size = 10;
        page = page - 1;

        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageDao.findAll(example, pageable);

        // List<CmsPage> collect = all.get()
        //         .sorted(Comparator.comparing(CmsPage::getPageCreateTime).reversed())
        //         .collect(Collectors.toList());

        QueryResult<CmsPage> queryResult = new QueryResult<>(all.getTotalElements(), all.getContent());

        return Result.SUCCESS(queryResult);
    }

    @Override
    public Result save(CmsPage cmsPage) {
        CmsPage cmsPage1 = cmsPageDao.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),
                cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (cmsPage1 != null) ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);

        cmsPage.setPageId(null);
        cmsPageDao.save(cmsPage);
        return new Result(CommonCode.SUCCESS,cmsPage);
    }

    @Override
    public CmsPage findById(String id) {
        Optional<CmsPage> optional = cmsPageDao.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Result update(String id, CmsPage cmsPage) {
        CmsPage one = this.findById(id);
        if (cmsPage != null) {
            //更新模板id
            one.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            one.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            one.setPageName(cmsPage.getPageName());
            //更新访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            one.setDataUrl(cmsPage.getDataUrl());
            one.setPageType(cmsPage.getPageType());
            one.setPageCreateTime(cmsPage.getPageCreateTime());

            cmsPageDao.save(one);
            return Result.SUCCESS(one);
        }
        return Result.FAIL();
    }

    @Override
    public Result delete(String id) {
        Optional<CmsPage> optional = cmsPageDao.findById(id);
        if (optional.isPresent()) {
            cmsPageDao.deleteById(id);
            return Result.SUCCESS();
        }
        return Result.FAIL();
    }

    @Override
    public Result post(String pageId) {
        String pageHtml = this.getPageHtml(pageId);
        this.saveHtml(pageId, pageHtml);
        this.sendPostPage(pageId);
        return Result.SUCCESS();
    }



    @Override
    public Result addAndUpdate(CmsPage cmsPage) {
        CmsPage page = cmsPageDao.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),
                cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (page != null){
            return this.update(page.getPageId(), cmsPage);
        }
        return this.save(cmsPage);
    }

    @Override
    public Result postPageQuick(CmsPage cmsPage) {
        Result result = this.addAndUpdate(cmsPage);
        if (!result.getSuccess()){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        CmsPage cmsPage1 = objectMapper.convertValue(result.getData(), CmsPage.class);
        String pageId = cmsPage1.getPageId();
        Result post = this.post(pageId);
        if (!post.getSuccess()){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        String siteId = cmsPage1.getSiteId();
        Optional<CmsSite> optional = cmsSiteDao.findById(siteId);
        if (optional.isPresent()){
            CmsSite cmsSite = optional.get();
            String pageUrl = cmsSite.getSiteDomain() + cmsPage1.getPageWebPath() + cmsPage1.getPageName();
            return Result.SUCCESS(pageUrl);
        }
        return Result.FAIL();
    }

    @Override
    public CmsConfig getModel(String id) {
        return cmsConfigDao.findById(id).orElse(null);
    }


    /**
     * Freemarker根据模型数据和页面模板获取Html文件
     * */
    public String getPageHtml (String pageId) {
        Map model = this.getModelByPageId(pageId);
        if (model == null) ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        String template = this.getTemplateByPageId(pageId);
        if (template == null) ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);

        return this.generateHtml(template, model);
    }

    /**
     * 获取模型数据
     * */
    private Map getModelByPageId(String pageId){
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null) ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);

        String dataUrl = cmsPage.getDataUrl();
        if (dataUrl.isEmpty()) ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);

        ResponseEntity<Map> model = restTemplate.getForEntity(dataUrl, Map.class);
        return model.getBody();
    }

    /**
     * 获取模板
     * */
    private String getTemplateByPageId(String pageId) {
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null) ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);

        String templateId = cmsPage.getTemplateId();
        if (templateId.isEmpty()) ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);

        Optional<CmsTemplate> template = cmsTemplateDao.findById(templateId);
        if (template.isPresent()) {
            CmsTemplate cmsTemplate = template.get();
            String templateFileId = cmsTemplate.getTemplateFileId();

            // 查询文件 Criteria对象拼接查询条件
            GridFSFile gridFSFile = gridFsTemplate
                    .findOne(Query.query(Criteria.where("_id").is(templateFileId)));
            // 获取文件下载流
            assert gridFSFile != null;
            GridFSDownloadStream stream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            GridFsResource resource = new GridFsResource(gridFSFile, stream);
            // 转String输出
            try {
                return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 生成静态HTML页面
     * */
    private String generateHtml(String templateContent, Map model){
        // 创建配置
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 创建模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        // 第一个参数：内存中模板的名字
        stringTemplateLoader.putTemplate("template",templateContent);
        // 向configuration配置加模板加载器
        configuration.setTemplateLoader(stringTemplateLoader);

        try {
            // 向FreeMarker中获取模板
            Template template = configuration.getTemplate("template");
            // FreeMarkerTemplateUtils工具生成HTML
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 保存HTML文件到mongodb GridFs
     * */
    private CmsPage saveHtml(String pageId, String htmlContent){
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        ObjectId objectId = null;
        try {
            InputStream inputStream = IOUtils.toInputStream(htmlContent, "utf-8");
            objectId = gridFsTemplate.store(inputStream, cmsPage.getPageName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cmsPage.setHtmlFileId(objectId != null ? objectId.toString() : null);
        cmsPageDao.save(cmsPage);
        return cmsPage;
    }

    /**
     * 向MQ发送消息
     * */
    private void sendPostPage(String pageId){
        CmsPage cmsPage = this.findById(pageId);
        HashMap<String, String> map = new HashMap<>();
        map.put("pageId",pageId);
        String msg = JSON.toJSONString(map);
        rabbitTemplate.convertAndSend("ex_routing_cms_postpage",cmsPage.getSiteId(),msg);
    }
}
