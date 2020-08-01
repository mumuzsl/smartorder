package com.cqjtu.smartorder.dao.dataobject;

import lombok.Data;

/**
 * @author mumu
 * @date 2020/7/23
 */
@Data
public class DishesDO {
    private Integer id;

    private String name;

    private Float price;

    private String img;

    private Integer recommend;

    private String txt;

    private String description;
}
