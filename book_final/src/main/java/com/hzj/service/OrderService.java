package com.hzj.service;

import com.hzj.pojo.Cart;
import com.hzj.pojo.Order;
import com.hzj.pojo.OrderItem;

import java.util.List;

/**
 * @author hzj
 * @create 2022-03-03 10:42
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
    public List<Order> showAllOrders();
    public void sendOrder(String orderId);
    public List<OrderItem> showOrderDetails(String orderId);
    public List<Order> showMyOrders(Integer userId);
    public void receiverOrder(String orderId);
}
