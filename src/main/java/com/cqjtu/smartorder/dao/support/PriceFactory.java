package com.cqjtu.smartorder.dao.support;

/**
 * @author mumu
 * @date 2020/11/9
 */
public abstract class PriceFactory<T> {

    abstract Valuable create(T t);

}
