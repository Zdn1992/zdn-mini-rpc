package com.zdn.product.bean;

import java.io.Serializable;

public class Product implements Serializable {
    
    private static final long serialVersionUID = 6628288808247169049L;
    
    /**
    * id
    */
    private Long id;

    /**
    * 
    */
    private String name; 
    
    /**
    * price
    */
    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
