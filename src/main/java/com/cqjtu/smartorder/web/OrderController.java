package com.cqjtu.smartorder.web;

import com.cqjtu.smartorder.api.OrderService;
import com.cqjtu.smartorder.api.UserService;
import com.cqjtu.smartorder.api.model.Order;
import com.cqjtu.smartorder.api.model.OrderDetail;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.api.param.OrderParam;
import com.cqjtu.smartorder.api.support.Result;
import com.cqjtu.smartorder.dao.dataobject.OrderDO;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.global.OrderStatusEnum;
import com.cqjtu.smartorder.util.EasyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/24
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Resource
    private UserService userService;


    @GetMapping("list")
    public Page list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                     @RequestParam(value = "status", required = false, defaultValue = "0") Integer status) {
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(status);
        return orderStatusEnum == null ? orderService.page(PageRequest.of(page, size))
                : orderService.pageByStatus(PageRequest.of(page, size), orderStatusEnum);
    }

    @GetMapping("{id:\\d+}")
    public Order get(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("modify")
    public Object modify(@RequestBody OrderDO orderDO) {
        if (orderDO.getId() == null) {
            return Result.build().fail();
        }
        orderService.save(orderDO);
        return orderDO.getUid();
    }

    @PostMapping("new")
    public Object newOrder(@RequestBody OrderParam orderParam) {
        Integer id = EasyUtils.authenticationUsername()
                .map(userService::getByUsername)
                .map(User::getId)
                .orElse(null);

        if (id == null) {
            return Result.build().fail();
        }

        orderParam.setUserId(id);

        return Result.build().ok().data(orderService.newOrder(orderParam));
    }

    @GetMapping("over")
    public Object overOrder(@RequestParam("id") Integer orderid) {
        return Result.build().ok().data(orderService.overOrder(orderid));
    }

    @GetMapping("chef")
    public Object chef(@RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       @RequestParam(value = "status", defaultValue = "0") Integer status) {
        return orderService.pageByOrderDetailStatus(PageRequest.of(page, size), OrderStatusEnum.valueOf(status));
    }

    @GetMapping("dishes/finish")
    public Object finish(@RequestParam(value = "id") Integer id) {
        return orderService.finishOrderDetail(id);
    }

    @PostMapping("start")
    public Object start(@RequestBody OrderDO orderDO) {
        orderDO.setId(null);
        orderDO.setEndTime(null);

        orderDO.setUid(EasyUtils.getLongId());
        orderDO.setStartTime(new Date());
        orderService.save(orderDO);
        return orderDO.getUid();
    }

    @PostMapping("end/{uid:\\d+}")
    public Object end(@RequestBody List<OrderDetail> orderDetails, @PathVariable("uid") Long uid) {
        return orderService.end(orderDetails, uid);
    }

    @GetMapping("statistic")
    public Object statistic(@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return orderService.statistic(PageRequest.of(page, size));
    }
}
