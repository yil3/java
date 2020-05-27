package com.ihrm;

import com.ihrm.dao.RoleDao;
import com.ihrm.dao.UserDao;
import com.ihrm.entity.Role;
import com.ihrm.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class Test2 {
  @Resource
  private UserDao userDao;

  @Resource
  private RoleDao roleDao;

  @Test
  public void test () throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date startTime = simpleDateFormat.parse("2018-11-2");
    Date endTime = simpleDateFormat.parse("2019-11-22");

    Specification<User> spec = new Specification<User>() {
      @Override
      public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate p1 = criteriaBuilder.between(root.get("createTime"), startTime, endTime);
        Predicate p2 = criteriaBuilder.ge(root.get("id"), 10637054829397L);
        return criteriaBuilder.and(p1,p2);
      }
    };
    List<User> list = userDao.findAll(spec);
    list.forEach(System.out::println);
  }

  @Test
  public void test2(){

    Specification<User> spec = new Specification<User>() {
      @Override
      public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("username"),"%a%");
      }
    };
    Sort sort = Sort.by(Sort.Direction.DESC,"id");
    List<User> list = userDao.findAll(spec,sort);
    list.forEach(System.out::println);
  }

  @Test
  public void test3(){
    PageRequest pageRequest = PageRequest.of(0, 2, Sort.Direction.DESC,"id");

    Page<User> userPage = userDao.findAll(pageRequest);

    System.out.println(userPage.getTotalElements());
    System.out.println(userPage.getTotalPages());
    System.out.println(userPage.getSort());
    List<User> content = userPage.getContent();
    content.forEach(System.out::println);
  }


}
