package com.hzj.dao.impl;

import com.hzj.dao.OrderItemDao;
import com.hzj.pojo.OrderItem;

import java.util.List;

/**
 * @author hzj
 * @create 2022-03-03 10:28
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        return null;
    }
}
