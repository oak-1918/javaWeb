package com.hzj.dao.impl;

import com.hzj.dao.OrderDao;
import com.hzj.pojo.Order;

import java.util.List;

/**
 * @author hzj
 * @create 2022-03-03 10:24
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
//        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        return null;
    }

    @Override
    public boolean changeOrDerStatus(String orderId, Integer status) {
        return false;
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        return null;
    }
}
