package com.cqjtu.smartorder.dao.dataobject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author mumu
 * @date 2020/7/23
 */
@Data
public class OrderDO {
    private Integer id;

    private Long uid;

    private Integer userId;

    private Integer deskNum;

    private Date startTime;

    private Date endTime;

    private Float amount;

    private Integer status;
}
