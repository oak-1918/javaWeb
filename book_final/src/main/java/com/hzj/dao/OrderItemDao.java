package com.hzj.dao;

import com.hzj.pojo.Order;
import com.hzj.pojo.OrderItem;

import java.util.List;

/**
 * @author hzj
 * @create 2022-03-03 10:08
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
