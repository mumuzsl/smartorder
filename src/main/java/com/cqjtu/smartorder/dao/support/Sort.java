package com.cqjtu.smartorder.dao.support;

/**
 * @author mumu
 * @date 2020/7/28
 */
public enum Sort {

    //
    DESC("DESC"),

    //
    ASC("ASC");

    private String value;

    Sort(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
