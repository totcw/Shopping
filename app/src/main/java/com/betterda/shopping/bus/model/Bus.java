package com.betterda.shopping.bus.model;

/**
 * 购物车商品的类
 * Created by Administrator on 2016/5/17.
 */
public class Bus {
    private String shopcartDetailId;//商品的id
    private boolean isChosed; //是否选择
    private String littlePicture; //图片的地址
    private String productName;//商品名字
    private String salePrice;//售价
    private String totalCount;//选择的数量
    private String vipPrice;//会员价
    private String spec;//规格

    public String getShopcartDetailId() {
        return shopcartDetailId;
    }

    public void setShopcartDetailId(String shopcartDetailId) {
        this.shopcartDetailId = shopcartDetailId;
    }

    public boolean isChosed() {
        return isChosed;
    }

    public void setChosed(boolean chosed) {
        isChosed = chosed;
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

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "shopcartDetailId='" + shopcartDetailId + '\'' +
                ", isChosed=" + isChosed +
                ", littlePicture='" + littlePicture + '\'' +
                ", productName='" + productName + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", vipPrice='" + vipPrice + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }
}
