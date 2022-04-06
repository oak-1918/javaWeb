package com.hzj.service.impl;

import com.alibaba.druid.sql.dialect.odps.ast.OdpsReadStatement;
import com.hzj.dao.BookDao;
import com.hzj.dao.OrderDao;
import com.hzj.dao.OrderItemDao;
import com.hzj.dao.impl.BookDaoImpl;
import com.hzj.dao.impl.OrderDaoImpl;
import com.hzj.dao.impl.OrderItemDaoImpl;
import com.hzj.pojo.*;
import com.hzj.service.OrderService;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hzj
 * @create 2022-03-03 10:42
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号---唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(), cart.getTotalPrice(), 0 , userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中的每一个商品项转换成为订单项保存到数据库
//        int i= 12 / 0;425245

        for(Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(),cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新销量和库存
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return null;
    }

    @Override
    public void sendOrder(String orderId) {

    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return null;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return null;
    }

    @Override
    public void receiverOrder(String orderId) {

    }
}
