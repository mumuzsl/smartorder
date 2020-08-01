package com.cqjtu.smartorder.api.model;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Data
public class Order {
    private Integer id;

    private Long uid;

    private Integer userId;

    private String username;

    private Integer deskNum;

    private Date startTime;

    private Date endTime;

    private Float amount;

    private Integer status;

    private List<OrderDetail> orderDetails;

    public String getStartTime() {
        return DateUtil.formatDateTime(startTime);
    }

    public String getEndTime() {
        return DateUtil.formatDateTime(endTime);
    }
}
