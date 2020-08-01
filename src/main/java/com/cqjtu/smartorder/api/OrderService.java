package com.cqjtu.smartorder.api;

import com.cqjtu.smartorder.api.model.Order;
import com.cqjtu.smartorder.api.model.OrderDetail;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.api.param.OrderParam;
import com.cqjtu.smartorder.dao.dataobject.OrderDO;
import com.cqjtu.smartorder.dao.dataobject.OrderDetailDO;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.global.OrderStatusEnum;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/24
 */
public interface OrderService extends BaseService<OrderDO> {

    Order getOrderById(Integer id);

    int end(List<OrderDetail> orderDetails, Long uid);

    Page<Order> page(PageRequest pageRequest);

    Long newOrder(OrderParam orderParam);

    int overOrder(Integer orderId);

    Page pageByStatus(PageRequest pageRequest, OrderStatusEnum status);

    Page<OrderDetail> pageByOrderDetailStatus(PageRequest pageRequest, OrderStatusEnum status);

    int finishOrderDetail(Integer orderDetailId);

    Page<Object> statistic(PageRequest pageRequest);
}
