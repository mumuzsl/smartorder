package com.cqjtu.smartorder.api.model;

import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author mumu
 * @date 2020/7/24
 */
public class BaseModel<T> {

    protected Boolean isUseConverter() {
        return false;
    }

    protected BeanCopier copier = BeanCopier.create(this.getClass(), getTargetType(), isUseConverter());

    private Class getTargetType() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        }
        return Object.class;
    }

}
