package com.cqjtu.smartorder.api.model;

import lombok.Data;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/26
 */
@Data
public class Menu {
    private Long id;

    private Long pid;

    private String title;

    private String icon;

    private String href;

    private String target;

    private List<Menu> child;
}
