package com.hzj.test;

import com.hzj.pojo.Cart;
import com.hzj.pojo.CartItem;
import com.hzj.pojo.OrderItem;
import com.hzj.service.OrderService;
import com.hzj.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author hzj
 * @create 2022-03-03 11:04
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"java11",1,new BigDecimal(1000),new BigDecimal(1000)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println("dingdanhao:" + orderService.createOrder(cart, 1));

    }

    @Test
    public void showAllOrders() {
    }

    @Test
    public void sendOrder() {
    }

    @Test
    public void showOrderDetails() {
    }

    @Test
    public void showMyOrders() {
    }

    @Test
    public void receiverOrder() {
    }
}