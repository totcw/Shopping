package com.betterda.shopping.shouye.model;

/**
 * 从首页跳到分类 类型和品牌类
 * Created by Administrator on 2017/2/14.
 */

public class Chose {
    private String productType;//商品类型
    private String pinpai;//商品品牌

    public Chose(String productType, String pinpai) {
        this.productType = productType;
        this.pinpai = pinpai;
    }

    public Chose() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPinpai() {
        return pinpai;
    }

    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }
}
