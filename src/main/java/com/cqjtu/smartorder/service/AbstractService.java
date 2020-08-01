package com.cqjtu.smartorder.service;

import com.cqjtu.smartorder.api.BaseService;
import com.cqjtu.smartorder.dao.dataobject.UserDO;
import com.cqjtu.smartorder.dao.mapper.MyBaseMapper;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/24
 */
public abstract class AbstractService<T> implements BaseService<T> {

    private MyBaseMapper<T> baseMapper;
    private BeanCopier copier;
    private Class tCalss;

    protected Logger logger = LoggerFactory.getLogger(AbstractService.class);

    private void buildCopier() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            tCalss = (Class) parameterizedType.getActualTypeArguments()[0];
//            Class class2 = (Class) parameterizedType.getActualTypeArguments()[1];
//            copier = BeanCopier.create(tCalss, class2, false);
        }
    }

    public AbstractService(MyBaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public Integer count() {
        return baseMapper.count();
    }

    @Override
    public Page<T> pageBy(PageRequest pageRequest) {
        return Page.of(pageRequest, count(), baseMapper.findAll(pageRequest));
    }

    @Override
    public Optional<T> getById(Integer id) {
        return Optional.ofNullable(baseMapper.getById(id));
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Throwable.class})
    @Override
    public int save(T entity) {
        try {
            if (BeanUtils.hasMethodNoCatch(entity, "getId") == null) {
                return baseMapper.insert(entity);
            } else {
                return baseMapper.updateById(entity);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error("Save entity failure: ", e);
            return 0;
        }
    }

    @Override
    public int remove(List<T> entities) {
        return baseMapper.deletes(entities);
    }
}
