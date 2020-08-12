package com.xuecheng.manage_course.service;

import com.xuecheng.service.course.CourseMarketService;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.model.response.Result;
import com.xuecheng.manage_course.dao.CourseMarketDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CourseMarketServiceImpl implements CourseMarketService {
    @Resource
    CourseMarketDao courseMarketDao;

    @Override
    @Transactional
    public Result saveAndEdit(String id, CourseMarket courseMarket) {
        Optional<CourseMarket> optional = courseMarketDao.findById(id);
        if (optional.isPresent()){
            CourseMarket market = optional.get();
            Float price = market.getPrice();
            BeanUtils.copyProperties(courseMarket,market);
            courseMarket.setPrice_old(price);
            courseMarketDao.save(market);
        }
        CourseMarket market = new CourseMarket();
        BeanUtils.copyProperties(courseMarket,market);
        market.setId(id);
        courseMarketDao.save(market);
        return Result.SUCCESS();
    }

    @Override
    public Result findById(String id) {
        CourseMarket courseMarket = courseMarketDao.findById(id).orElse(null);
        return Result.SUCCESS(courseMarket);
    }


}
