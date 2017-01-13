package com.betterda.shopping.javabean;

/**
 * 商品详情
 * Created by Administrator on 2017/1/5.
 */

public class ShopDetail {
   // productName：商品名称；salePrice：原价；vipPrice：会员价；spec：规格；
   // quantity；商品的评价数量；degree：好评度；bigPicture：商品大图片。,购物车数量
    private String productName;
    private String salePrice;
    private String vipPrice;
    private String spec;
    private String quantity;
    private String degree;
    private String bigPicture;

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBigPicture() {
        return bigPicture;
    }

    public void setBigPicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    @Override
    public String toString() {
        return "ShopDetail{" +
                "productName='" + productName + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", vipPrice='" + vipPrice + '\'' +
                ", spec='" + spec + '\'' +
                ", quantity='" + quantity + '\'' +
                ", degree='" + degree + '\'' +
                ", bigPicture='" + bigPicture + '\'' +
                '}';
    }
}
