package com.cqjtu.smartorder.dao.mapper;

import com.cqjtu.smartorder.api.model.OrderDetail;
import com.cqjtu.smartorder.dao.dataobject.OrderDetailDO;
import com.cqjtu.smartorder.dao.support.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Mapper
public interface OrderDetailMapper extends MyBaseMapper<OrderDetailDO> {
    List<OrderDetail> listByOrderId(Integer orderId);

    List<OrderDetail> findAllByStatus(@Param("pageRequest") PageRequest pageRequest,
                                      @Param("status") Integer status);

    Integer count(Integer status);

    Integer countByStatus(Integer status);

    Integer finish(Integer id);

    Integer statisticCount();

    List<Object> statistic(PageRequest pageRequest);
}
