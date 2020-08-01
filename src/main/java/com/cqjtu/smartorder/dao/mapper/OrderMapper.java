package com.cqjtu.smartorder.dao.mapper;

import com.cqjtu.smartorder.api.model.Order;
import com.cqjtu.smartorder.dao.dataobject.OrderDO;
import com.cqjtu.smartorder.dao.support.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Mapper
public interface OrderMapper extends MyBaseMapper<OrderDO> {
    OrderDO getByUid(Long uid);

    Order getOrderById(Integer id);

    List<Order> findAllOrder(PageRequest pageRequest);

    List<OrderDO> findAllByStatus(@Param("pageRequest") PageRequest pageRequest,
                                  @Param("status") Integer status);

    Integer countByStatus(Integer status);

    Integer getMaxId();
}
