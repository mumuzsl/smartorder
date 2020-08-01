package com.cqjtu.smartorder.api.vo;

import lombok.Data;

/**
 * @author mumu
 * @date 2020/7/29
 */
@Data
public class DishesStatisticVO {

    private Integer dishesId;
    private Integer count;
    private Float amount;
    private String dishesName;

}
