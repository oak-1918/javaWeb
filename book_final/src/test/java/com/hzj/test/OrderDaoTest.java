package com.hzj.test;

import com.hzj.dao.OrderDao;
import com.hzj.dao.impl.OrderDaoImpl;
import com.hzj.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hzj
 * @create 2022-03-03 10:33
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }
}