package com.cqjtu.smartorder.dao.mapper;

import com.cqjtu.smartorder.dao.support.PageRequest;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/24
 */
public interface MyBaseMapper<T> {

    List<T> findAll();

//    List<T> findAll(@Param("page") Integer page, @Param("size") Integer size);

    List<T> findAll(PageRequest pageRequest);

    Integer count();

    T getById(Integer id);

    int insert(T entity);

    int updateById(T entity);

    int deletes(List<T> entities);

//    List<T> findAllOrder(PageRequest request, String[] columns, String[] sorts);
}
