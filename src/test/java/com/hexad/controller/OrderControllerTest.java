package com.hexad.controller;

import com.hexad.model.ProductPack;
import com.hexad.persistence.MockProductDao;
import com.hexad.persistence.MockProductPackDao;
import com.hexad.persistence.ProductDao;
import com.hexad.persistence.ProductPackDao;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class OrderControllerTest {
    private final ProductDao  productDao;
    private final ProductPackDao productPackDao;
    private final OrderController controller;
    
    public OrderControllerTest() {
        productDao = new MockProductDao();
        productPackDao = new MockProductPackDao();
        
        controller = new OrderController(productDao, productPackDao);
    }
    
    @Test
    public void buildOrderShouldReturnProperOrderMakeupWhenValidInput() {
        Map<ProductPack, Integer> orderMakeup;
        List<Map.Entry> orderItems;
        
        orderMakeup = controller.buildOrder("10 VS5");
        orderItems = getSortedOrderItems(orderMakeup);
        ProductPack key = (ProductPack)orderItems.get(0).getKey();
        Integer value = (Integer)orderItems.get(0).getValue();
        Assert.assertEquals(5L, (long)key.getAmmount());
        Assert.assertEquals(2L, (long)value);
        printOutput(orderMakeup);
        
        orderMakeup = controller.buildOrder("14 MB11");
        orderItems = getSortedOrderItems(orderMakeup);
        key = (ProductPack)orderItems.get(0).getKey();
        value = (Integer)orderItems.get(0).getValue();
        Assert.assertEquals(8L, (long)key.getAmmount());
        Assert.assertEquals(1L, (long)value);
        key = (ProductPack)orderItems.get(1).getKey();
        value = (Integer)orderItems.get(1).getValue();
        Assert.assertEquals(2L, (long)key.getAmmount());
        Assert.assertEquals(3L, (long)value);
        printOutput(orderMakeup);
        
        orderMakeup = controller.buildOrder("13 CF");
        orderItems = getSortedOrderItems(orderMakeup);
        key = (ProductPack)orderItems.get(0).getKey();
        value = (Integer)orderItems.get(0).getValue();
        Assert.assertEquals(5L, (long)key.getAmmount());
        Assert.assertEquals(2L, (long)value);
        key = (ProductPack)orderItems.get(1).getKey();
        value = (Integer)orderItems.get(1).getValue();
        Assert.assertEquals(3L, (long)key.getAmmount());
        Assert.assertEquals(1L, (long)value);
        printOutput(orderMakeup);
    }
    
    private List<Map.Entry> getSortedOrderItems(Map<ProductPack, Integer> orderMakeup) {
        List<Map.Entry> orderItems = new ArrayList<>();
        for (Map.Entry orderItem : orderMakeup.entrySet()) {
            orderItems.add(orderItem);
        }
        
        orderItems.sort((e1, e2) -> {
            return ((ProductPack)e2.getKey()).compareTo((ProductPack)e1.getKey());
        });
        
        return orderItems;
    }
    
    private void printOutput(Map<ProductPack, Integer> orderMakeup) {
        ProductPack key;
        Integer value;
        int totalAmmount = 0;
        String code = null;
        double totalPrice = 0;
        for (Map.Entry orderItem : orderMakeup.entrySet()) {
            key = (ProductPack)orderItem.getKey();
            value = (Integer)orderItem.getValue();
            totalAmmount +=  key.getAmmount() * value;
            code = key.getProduct().getCode();
            totalPrice += key.getPrice();
        }
        System.out.printf("%d %s $%.2f\n", totalAmmount, code, totalPrice);
        for (Map.Entry orderItem : orderMakeup.entrySet()) {
            key = (ProductPack)orderItem.getKey();
            value = (Integer)orderItem.getValue();
            System.out.printf("\t%d x %d $%.2f\n", value, key.getAmmount(), key.getPrice());
        }
    }
}
