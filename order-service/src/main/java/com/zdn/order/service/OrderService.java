package com.zdn.order.service;

public interface OrderService {

    /**
     * 根据商品号生成订单
     * @param productId
     */
    void createOrder(long productId);
}
