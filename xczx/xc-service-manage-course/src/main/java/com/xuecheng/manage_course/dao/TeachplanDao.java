package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachplanDao extends JpaRepository<Teachplan,String> {
    List<Teachplan> findByCourseidAndParentid(String courseid, String parentid);
}
