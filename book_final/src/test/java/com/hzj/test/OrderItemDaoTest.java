package com.hzj.test;

import com.hzj.dao.OrderItemDao;
import com.hzj.dao.impl.OrderItemDaoImpl;
import com.hzj.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author hzj
 * @create 2022-03-03 10:40
 */
public class OrderItemDaoTest {

    @Test
    public void queryOrderItemsByOrderId() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript 从入门到精通", 2,new
                BigDecimal(100),new BigDecimal(200),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567891"));
    }
}