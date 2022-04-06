package com.hzj.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hzj
 * @create 2022-03-02 19:28
 */
public class Cart {

    private Map<Integer, CartItem> items= new LinkedHashMap<>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem cartItem1 = items.get(cartItem.getId());
        if(cartItem1 == null){
            items.put(cartItem.getId(),cartItem);
        }else{
            cartItem1.setCount(cartItem1.getCount() + 1);
            cartItem1.setTotalPrice(cartItem1.getPrice().multiply(new BigDecimal(cartItem1.getCount())));
        }
    }

    /**
     * 添加商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id, Integer count){
        CartItem item = items.get(id);
        if(item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }




    public Cart() {
    }

    public Cart( Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
