package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.CoursePic;
import com.xuecheng.framework.model.response.Result;
import com.xuecheng.manage_course.dao.CoursePicDao;
import com.xuecheng.service.course.CoursePicService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CoursePicServiceImpl implements CoursePicService {
    @Resource
    CoursePicDao coursePicDao;

    @Override
    @Transactional
    public Result addCoursePic(String courseId, String picId) {
        CoursePic coursePic = null;
        Optional<CoursePic> optional = coursePicDao.findById(courseId);
        if (optional.isPresent()) {
            coursePic = optional.get();
        }
        if (coursePic == null) {
            coursePic = new CoursePic();
            coursePic.setCourseid(courseId);
            coursePic.setPic(picId);
        }
        coursePicDao.save(coursePic);
        return Result.SUCCESS();
    }

    @Override
    public Result findCoursePicList(String courseId) {
        CoursePic coursePic = new CoursePic();
        coursePic.setCourseid(courseId);
        Example<CoursePic> example = Example.of(coursePic);
        List<CoursePic> list = coursePicDao.findAll(example);
        return Result.SUCCESS(list);
    }

    @Override
    @Transactional
    public Result deleteCoursePic(String courseId){
        Long aLong = coursePicDao.deleteByCourseid(courseId);
        if (aLong > 0) {
            return Result.SUCCESS();
        }
        return Result.FAIL();
    }
}
