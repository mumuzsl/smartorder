package com.cqjtu.smartorder.service;

import com.cqjtu.smartorder.api.model.Order;
import com.cqjtu.smartorder.api.model.OrderDetail;
import com.cqjtu.smartorder.api.param.OrderDetailParam;
import com.cqjtu.smartorder.api.param.OrderParam;
import com.cqjtu.smartorder.dao.dataobject.OrderDO;
import com.cqjtu.smartorder.dao.dataobject.OrderDetailDO;
import com.cqjtu.smartorder.dao.mapper.DishesMapper;
import com.cqjtu.smartorder.dao.mapper.OrderDetailMapper;
import com.cqjtu.smartorder.dao.mapper.OrderMapper;
import com.cqjtu.smartorder.api.OrderService;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.dao.support.Valuable;
import com.cqjtu.smartorder.dao.support.Valuation;
import com.cqjtu.smartorder.global.OrderStatusEnum;
import com.cqjtu.smartorder.util.EasyUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Service
public class OrderServiceImpl extends AbstractService<OrderDO> implements OrderService {

    private OrderMapper orderMapper;
    private OrderDetailMapper orderDetailMapper;
    private DishesMapper dishesMapper;

    private static final BeanCopier copier1 = BeanCopier.create(OrderDO.class, Order.class, false);
    private static final BeanCopier copier2 = BeanCopier.create(Order.class, OrderDO.class, false);
    private static final BeanCopier copier3 = BeanCopier.create(OrderDetail.class, OrderDetailDO.class, false);

//    private static final Integer UNFINISHED = 0;

    public OrderServiceImpl(OrderMapper orderMapper, OrderDetailMapper orderDetailMapper, DishesMapper dishesMapper) {
        super(orderMapper);
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.dishesMapper = dishesMapper;
    }

    @Override
    public Page<Order> page(PageRequest pageRequest) {
        return Page.of(pageRequest, count(), orderMapper.findAllOrder(pageRequest));
    }

    @Override
    public Order getOrderById(Integer id) {
        Order order = orderMapper.getOrderById(id);

        getById(id).ifPresent(orderDO -> {
            copier1.copy(orderDO, order, null);

            List<OrderDetail> orderDetails = orderDetailMapper.listByOrderId(id);
            order.setOrderDetails(orderDetails);
        });

        return order;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Throwable.class})
    @Override
    public int end(List<OrderDetail> orderDetails, Long uid) {
        if (uid == null) {
            logger.error("end failure: don't has uid");
            return 0;
        }

        OrderDO orderDO = orderMapper.getByUid(uid);

        if (orderDO == null) {
            logger.error("end failure: uid has no corresponding orderDO");
            return 0;
        } else if (orderDO.getStatus() != OrderStatusEnum.UNFINISHED.value()) {
            logger.error("end failure: yet end");
            return 0;
        }

        orderDO.setAmount(0f);

        orderDetails.forEach(orderDetail -> {
            Float price = dishesMapper.getPriceById(orderDetail.getDishesId());

            OrderDetailDO orderDetailDO = new OrderDetailDO();
            copier3.copy(orderDetail, orderDetailDO, null);
            orderDetailDO.setOrderId(orderDO.getId());
            orderDetailDO.setPrice(price);
            orderDetailMapper.insert(orderDetailDO);

            Float amount = orderDO.getAmount();
            orderDO.setAmount(amount + orderDetail.getCount() * price);
        });

        orderDO.setEndTime(new Date());
        return save(orderDO);
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Throwable.class})
    @Override
    public Long newOrder(OrderParam orderParam) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(null);
        orderDO.setUid(EasyUtils.getLongId());
        orderDO.setStartTime(new Date());
        orderDO.setDeskNum(orderParam.getDeskNum());
        orderDO.setStatus(0);
        orderDO.setUserId(orderParam.getUserId());
        orderDO.setAmount(0f);

        orderMapper.insert(orderDO);

        List<OrderDetailParam> dishesInfo = orderParam.getDishesInfo();

//        dishesInfo.forEach(param -> {
//            OrderDetailDO orderDetailDO = new OrderDetailDO();
//            orderDetailDO.setDishesId(param.getDishesId());
//            orderDetailDO.setPrice(dishesMapper.getPriceById(param.getDishesId()));
//            orderDetailDO.setCount(param.getCount());
//            orderDetailDO.setOrderId(orderDO.getId());
//
//            orderDetailMapper.insert(orderDetailDO);
//
//            final Float amount = orderDO.getAmount();
//            orderDO.setAmount(amount + orderDetailDO.getCount() * orderDetailDO.getPrice());
//        });

        Easy easy = new Easy();

        dishesInfo.forEach(
                param -> orderDetailMapper.insert(easy.get(param, orderDO.getId()))
        );

        orderDO.setAmount(easy.getAmount());
        orderMapper.updateById(orderDO);

        return orderDO.getUid();
    }

    private class Easy {
        private OrderDetailDO orderDetailDO = new OrderDetailDO();
        private Float amount = 0.0f;

        public OrderDetailDO get(OrderDetailParam param, Integer orderDOId) {
            orderDetailDO.setId(null);
            orderDetailDO.setDishesId(param.getDishesId());
            orderDetailDO.setPrice(dishesMapper.getPriceById(param.getDishesId()));
            orderDetailDO.setCount(param.getCount());
            orderDetailDO.setOrderId(orderDOId);

            add(orderDetailDO.getPrice() * orderDetailDO.getCount());

            return orderDetailDO;
        }

        public void add(Float value) {
            amount += value;
        }

        public void zone() {
            amount = 0.0f;
        }

        public Float getAmount() {
            return amount;
        }
    }

    private Valuable a(OrderDetailParam param) {
        return Valuation.of(param.getCount(), dishesMapper.getPriceById(param.getDishesId()));
    }

    @Override
    public int overOrder(Integer orderId) {
        Optional<OrderDO> orderDOOptional = getById(orderId);

        if (orderDOOptional.isPresent()) {
            OrderDO orderDO = orderDOOptional.get();
            orderDO.setEndTime(new Date());
            orderDO.setStatus(OrderStatusEnum.FINISH.value());
            return save(orderDO);
        }

        return 0;
    }

    @Override
    public Page<OrderDO> pageByStatus(PageRequest pageRequest, OrderStatusEnum status) {
        return Page.of(
                pageRequest,
                orderMapper.countByStatus(status.value()),
                orderMapper.findAllByStatus(pageRequest, status.value())
        );
    }

    @Override
    public Page<OrderDetail> pageByOrderDetailStatus(PageRequest pageRequest, OrderStatusEnum status) {
        return Page.of(
                pageRequest,
                orderDetailMapper.countByStatus(status.value()),
                orderDetailMapper.findAllByStatus(pageRequest, status.value())
        );
    }

    @Override
    public int finishOrderDetail(Integer orderDetailId) {
        return orderDetailMapper.finish(orderDetailId);
    }

    @Override
    public Page<Object> statistic(PageRequest pageRequest) {
        return Page.of(
                pageRequest,
                orderDetailMapper.statisticCount(),
                orderDetailMapper.statistic(pageRequest)
        );
    }
}
