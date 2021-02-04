package com.cqjtu.smartorder.dao.support;

/**
 * @author mumu
 * @date 2020/11/9
 */
public interface Valuable {

    Integer count();

    Float price();

    default Float mount() {
        return price() * count();
    }

}
