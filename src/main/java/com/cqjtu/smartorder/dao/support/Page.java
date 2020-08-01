package com.cqjtu.smartorder.dao.support;

import java.io.Serializable;
import java.util.List;

/**
 * @author mumu
 * @date 2020/7/24
 */
public class Page<T> {

    private Integer page;
    private Integer size;
    private Integer totalElements;
    private List<T> elements;

    public Page(Integer page, Integer size, Integer totalElements, List<T> elements) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.elements = elements;
    }

    public static <T> Page<T> of(PageRequest pageRequest,
                                 Integer totalElements,
                                 List<T> elements) {
        return new Page<>(pageRequest.getPage(), pageRequest.getSize(), totalElements, elements);
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) getSize());
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public List<T> getElements() {
        return elements;
    }

}
