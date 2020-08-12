package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePicDao extends JpaRepository<CoursePic,String> {
    List<CoursePic> findByCourseid (String courseId);
    Long deleteByCourseid(String courseId);
}
