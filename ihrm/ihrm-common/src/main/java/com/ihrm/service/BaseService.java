package com.ihrm.service;

import com.ihrm.exception.CommonException;

import java.util.List;

public interface BaseService<T> {
  /**
   * 新增对象.
   */
  public void save(T entity);

  /**
   * 修改对象.
   */
  public void updateById(T entity);


  /**
   * 通过id删除对象.
   */
  public void deleteById(Long id);

  /**
   * 按id获取对象.
   */
  public T findById(Long id);


  /**
   * 获取全部对象.
   */
  public List<T> findAll();


}