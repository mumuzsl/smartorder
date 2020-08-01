package com.cqjtu.smartorder.api.model;

import lombok.Data;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Data
public class OrderDetail {
    private Integer id;

    private Integer dishesId;

    private String dishesName;

    private Float price;

    private Integer count;

    private Integer status;

    private Integer deskNum;
}
