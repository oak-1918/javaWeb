package com.hzj.test;

import com.hzj.pojo.Cart;
import com.hzj.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author hzj
 * @create 2022-03-02 19:46
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"java11",1,new BigDecimal(1000),new BigDecimal(1000)));
        System.out.println(cart.getTotalPrice());
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"java11",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.deleteItem(1);
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"java11",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"java11",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.updateCount(1,5);
        System.out.println(cart);
    }
}