package com.xuecheng.manage_course.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import com.xuecheng.framework.domain.course.CoursePub;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.CourseCode;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.Result;
import com.xuecheng.manage_course.client.CmsPageClient;
import com.xuecheng.manage_course.dao.*;
import com.xuecheng.service.course.CourseViewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CourseViewServiceImpl implements CourseViewService {
    @Resource
    CourseBaseDao courseBaseDao;
    @Resource
    CourseMarketDao courseMarketDao;
    @Resource
    CoursePicDao coursePicDao;
    @Resource
    TeachplanMapper teachplanMapper;
    @Resource
    CmsPageClient cmsPageClient;
    @Resource
    CoursePubDao coursePubDao;
    @Value("${course-publish.siteId}")
    String siteId;
    @Value("${course-publish.pageWebPath}")
    String pageWebPath;
    @Value("${course-publish.pagePhysicalPath}")
    String pagePhysicalPath;
    @Value("${course-publish.templateId}")
    String templateId;
    @Value("${course-publish.dataUrl}")
    String dataUrl;
    @Value("${course-publish.preViewUrl}")
    String preViewUrl;

    @Override
    public CourseView getCourseView(String id) {
        CourseView courseView = new CourseView();
        TeachplanNode teachplanNode = teachplanMapper.selectList(id);
        CoursePic coursePic = coursePicDao.findById(id).orElse(null);
        CourseBase courseBase = courseBaseDao.findById(id).orElse(null);
        CourseMarket courseMarket = courseMarketDao.findById(id).orElse(null);
        courseView.setCourseBase(courseBase);
        courseView.setCourseMarket(courseMarket);
        courseView.setCoursePic(coursePic);
        courseView.setTeachplanNode(teachplanNode);
        return courseView;
    }

    @Override
    public CoursePublishResult preview(String id) {
        CourseBase courseBase = this.findCourseBaseById(id);
        CmsPage cmsPage = new CmsPage();

        cmsPage.setSiteId(siteId);
        cmsPage.setPageName(id + ".html");
        cmsPage.setDataUrl(dataUrl + id);
        cmsPage.setTemplateId(templateId);
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPageWebPath(pageWebPath);
        cmsPage.setPagePhysicalPath(pagePhysicalPath);
        cmsPage.setPageCreateTime(new Date());

        Result result = cmsPageClient.addAndUpdate(cmsPage);

        if (!result.getSuccess()){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        // 任意object对象转对应的entity类
        ObjectMapper objectMapper = new ObjectMapper();
        CmsPage cmsPage1 = objectMapper.convertValue(result.getData(), CmsPage.class);
        String pageId = cmsPage1.getPageId();


        String previewUrl = preViewUrl + pageId;
        return new CoursePublishResult(CommonCode.SUCCESS, previewUrl);
    }

    @Override
    @Transactional
    public CoursePublishResult publish(String id) {
        CourseBase courseBase = this.findCourseBaseById(id);
        CmsPage cmsPage = new CmsPage();

        cmsPage.setSiteId(siteId);
        cmsPage.setPageName(id + ".html");
        cmsPage.setDataUrl(dataUrl + id);
        cmsPage.setTemplateId(templateId);
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPageWebPath(pageWebPath);
        cmsPage.setPagePhysicalPath(pagePhysicalPath);
        cmsPage.setPageCreateTime(new Date());
        // 远程调用cms，保存/更新 页面
        Result result = cmsPageClient.postPageQuick(cmsPage);

        if (!result.getSuccess()){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        // 更改发布状态 "已发布"
        this.saveCourseBaseStatus(id);
        String url = (String) result.getData();
        // Logstash
        CoursePub coursePub = this.createCoursePub(id);
        this.saveCoursePub(id,coursePub);

        return new CoursePublishResult(CommonCode.SUCCESS,url);
    }

    public CourseBase findCourseBaseById(String courseId){
        Optional<CourseBase> optional = courseBaseDao.findById(courseId);
        if (optional.isPresent()){
            return optional.get();
        }
        ExceptionCast.cast(CourseCode.COURSE_GET_NOTEXISTS);
        return null;
    }

    private void saveCourseBaseStatus(String courseId){
        CourseBase courseBase = this.findCourseBaseById(courseId);
        courseBase.setStatus("202002");
        courseBaseDao.save(courseBase);
    }

    private CoursePub createCoursePub(String courseId){
        CoursePub coursePub = new CoursePub();
        CourseBase courseBase = this.findCourseBaseById(courseId);
        if (courseBase != null){
            BeanUtils.copyProperties(courseBase,coursePub);
        }
        Optional<CourseMarket> optional = courseMarketDao.findById(courseId);
        if (optional.isPresent()){
            CourseMarket courseMarket = optional.get();
            BeanUtils.copyProperties(courseMarket,coursePub);
        }
        Optional<CoursePic> optional1 = coursePicDao.findById(courseId);
        if (optional1.isPresent()){
            CoursePic coursePic = optional1.get();
            BeanUtils.copyProperties(coursePic,coursePub);
        }
        TeachplanNode teachplanNode = teachplanMapper.selectList(courseId);
        String jsonString = JSON.toJSONString(teachplanNode);
        coursePub.setTeachplan(jsonString);

        return coursePub;
    }

    public void saveCoursePub(String id, CoursePub coursePub){
        CoursePub coursePubNew = null;
        Optional<CoursePub> optional = coursePubDao.findById(id);
        coursePubNew = optional.orElseGet(CoursePub::new);
        BeanUtils.copyProperties(coursePub,coursePubNew);
        coursePubNew.setId(id);
        coursePubNew.setTimestamp(new Date());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        coursePubNew.setPubTime(date);
        coursePubDao.save(coursePubNew);
    }
}
