package com.cqjtu.smartorder.dao.support;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/24
 */
public class PageRequest {
    private Integer page;
    private Integer size;
    private Integer offset;
    private Integer rows;
    private List<ColumnSort> columnSorts;

    public PageRequest(Integer page, Integer size) {
        this(page, size, null);
    }

    public PageRequest(Integer page, Integer size, List<ColumnSort> columnSorts) {
        this.page = (page - 1) < 0 ? 0 : page - 1;
        this.size = size;
        this.offset = this.page * size;
        this.rows = size;
        this.columnSorts = columnSorts;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size);
    }

    public static PageRequest of(int page, int size, List<ColumnSort> columnSorts) {
        return new PageRequest(page, size, columnSorts);
    }
}
