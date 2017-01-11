package com.betterda.shopping.javabean;

/**
 * 商品品牌
 * Created by Administrator on 2017/1/11.
 */

public class ShopBrand {
    private String id;
    private String brand;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ShopBrand{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
