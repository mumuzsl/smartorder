package com.cqjtu.smartorder.api.param;

import com.cqjtu.smartorder.dao.support.Valuable;
import lombok.Data;

/**
 * @author mumu
 * @date 2020/7/29
 */
@Data
public class OrderDetailParam {

    private Integer dishesId;

    private Integer count;

}
