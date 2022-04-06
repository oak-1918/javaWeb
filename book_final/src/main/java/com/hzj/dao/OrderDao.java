package com.hzj.dao;

import com.hzj.pojo.Order;

import java.util.List;

/**
 * @author hzj
 * @create 2022-03-03 10:06
 */
public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrders();
    public boolean changeOrDerStatus(String orderId,Integer status);
    public List<Order> queryOrdersByUserId(Integer userId);
}
