package com.betterda.shopping.sort.model;

/**
 * 商品
 * Created by Administrator on 2016/12/13.
 */

public class Shopping {
    private String id;
    private  String littlePicture;//商品小图片
    private String productName;//商品名称
    private String  spec;//规格
    private String salePrice;//原价
    private String vipPrice;//会员价

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLittlePicture() {
        return littlePicture;
    }

    public void setLittlePicture(String littlePicture) {
        this.littlePicture = littlePicture;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }
}
