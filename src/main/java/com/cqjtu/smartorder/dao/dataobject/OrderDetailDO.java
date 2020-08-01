package com.cqjtu.smartorder.dao.dataobject;

import lombok.Data;

/**
 * @author mumu
 * @date 2020/7/23
 */
@Data
public class OrderDetailDO {
    private Integer id;

    private Integer orderId;

    private Integer dishesId;

    private Float price;

    private Integer count;
}
