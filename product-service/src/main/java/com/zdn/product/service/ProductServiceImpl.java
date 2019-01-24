package com.zdn.product.service;

import com.zdn.product.api.IProductService;
import com.zdn.product.bean.Product;

public class ProductServiceImpl implements IProductService{


    @Override
    public Product getProductById(long id) {

        Product product = new Product();
        product.setId(id);
        product.setName("hello rpc");
        product.setPrice(100.0);
        return product;
    }
}
