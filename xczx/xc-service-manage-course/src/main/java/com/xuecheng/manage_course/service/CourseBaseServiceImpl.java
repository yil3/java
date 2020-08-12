package com.xuecheng.manage_course.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.service.course.CourseBaseService;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.QueryCourseListResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.Result;
import com.xuecheng.manage_course.dao.CourseBaseDao;
import com.xuecheng.manage_course.dao.CourseBaseMapper;
import com.xuecheng.manage_course.dao.TeachplanDao;
import com.xuecheng.manage_course.dao.TeachplanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CourseBaseServiceImpl implements CourseBaseService {
    @Resource
    TeachplanMapper teachplanMapper;
    @Resource
    TeachplanDao teachplanDao;
    @Resource
    CourseBaseDao courseBaseDao;
    @Resource
    CourseBaseMapper courseBaseMapper;


    @Override
    public TeachplanNode findTeachplanList(String courseId) {
        return teachplanMapper.selectList(courseId);
    }

    @Override
    @Transactional
    public Result addTeachplan(Teachplan teachplan) {
        if (teachplan == null || StringUtils.isEmpty(teachplan.getPname()) ||
                StringUtils.isEmpty(teachplan.getCourseid())) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        String courseid = teachplan.getCourseid();
        String parentid = teachplan.getParentid();

        if (StringUtils.isEmpty(parentid)) {
            parentid = this.getTeachplanRoot(courseid);
        }
        assert parentid != null;
        Teachplan parent = teachplanDao.findById(parentid).get();
        String grade = parent.getGrade();

        Teachplan teachplanNew = new Teachplan();
        BeanUtils.copyProperties(teachplan, teachplanNew);
        teachplanNew.setParentid(parentid);
        teachplanNew.setCourseid(courseid);

        if (grade.equals("1")) {
            teachplanNew.setGrade("2");
        } else {
            teachplanNew.setGrade("3");
        }

        teachplanDao.save(teachplanNew);

        return Result.SUCCESS();
    }

    @Override
    public Result findCourseList(Integer page, Integer size, CourseListRequest courseListRequest) {
        if (courseListRequest == null) courseListRequest = new CourseListRequest();
        PageHelper.startPage(page,size);
        Page<QueryCourseListResult> courseList = courseBaseMapper.findCourseList(courseListRequest);
        List<QueryCourseListResult> result = courseList.getResult();
        long total = courseList.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",result);
        map.put("total",total);
        return Result.SUCCESS(map);
    }

    @Override
    @Transactional
    public Result addCourseBase(CourseBase courseBase) {
        return Result.SUCCESS(courseBaseDao.save(courseBase));
    }

    @Override
    public Result findById(String courseId) {
        return Result.SUCCESS(courseBaseDao.findById(courseId).orElse(null));
    }

    @Override
    @Transactional
    public Result editCourseBase(String courseId, CourseBase courseBase) {
        Optional<CourseBase> optional = courseBaseDao.findById(courseId);
        if (optional.isPresent()){
            CourseBase course = optional.get();
            course.setName(courseBase.getName());
            course.setMt(courseBase.getMt());
            course.setSt(courseBase.getSt());
            course.setUsers(courseBase.getUsers());
            course.setGrade(courseBase.getGrade());
            // course.setStatus(courseBase.getStatus());
            course.setDescription(courseBase.getDescription());
            course.setStudymodel(courseBase.getStudymodel());
            courseBaseDao.save(course);
            return Result.SUCCESS();
        }
        return Result.FAIL();
    }


    private String getTeachplanRoot(String courseId) {
        Optional<CourseBase> optional = courseBaseDao.findById(courseId);
        if (!optional.isPresent()) return null;
        CourseBase course = optional.get();
        List<Teachplan> teachplanList = teachplanDao.findByCourseidAndParentid(courseId, "0");
        if (teachplanList == null || teachplanList.size() <= 0) {
            Teachplan teachplan = new Teachplan();
            teachplan.setParentid("0");
            teachplan.setGrade("1");
            teachplan.setPname(course.getName());
            teachplan.setCourseid(courseId);
            teachplan.setStatus("0");
            teachplanDao.save(teachplan);
            return teachplan.getId();
        }
        return teachplanList.get(0).getId();
    }
}
