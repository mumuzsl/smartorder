package com.cqjtu.smartorder.api.param;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/29
 */
@Data
public class OrderParam {

    private Integer userId;

    private Integer deskNum;

    private List<OrderDetailParam> dishesInfo;

}
