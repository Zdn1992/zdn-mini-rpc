package com.zdn.order.service.impl;

import com.zdn.order.service.OrderService;
import org.junit.Test;


public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        OrderService orderService = new OrderServiceImpl();
        orderService.createOrder(1);
    }
}