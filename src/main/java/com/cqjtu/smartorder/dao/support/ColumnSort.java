package com.cqjtu.smartorder.dao.support;

/**
 * @author mumu
 * @date 2020/7/28
 */
public class ColumnSort {
    private String column;
    private String sort;

    public ColumnSort(String column, Sort sort) {
        this.column = column;
        this.sort = sort.value();
    }

    public ColumnSort(String column) {
        this(column, Sort.ASC);
    }

    public String getColumn() {
        return column;
    }

    public String getSort() {
        return sort;
    }
}
