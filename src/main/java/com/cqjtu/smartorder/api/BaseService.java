package com.cqjtu.smartorder.api;

import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/24
 */
public interface BaseService<T> {

    Page pageBy(PageRequest pageRequest);

    Integer count();

    Optional<T> getById(Integer id);

    int save(T entity);

    int remove(List<T> entities);

//    int saveModel(M entity);
}
