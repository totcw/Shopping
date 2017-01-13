package com.betterda.shopping.javabean;

/**
 * 添加评论
 * Created by Administrator on 2017/1/5.
 */

public class AddComment {
    private String shopcartDetailId;//商品的id
    private String shopName;
    private String shopUrl;
    private double shopStar;
    private String commentContent;

    public String getShopcartDetailId() {
        return shopcartDetailId;
    }

    public void setShopcartDetailId(String shopcartDetailId) {
        this.shopcartDetailId = shopcartDetailId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public double getShopStar() {
        return shopStar;
    }

    public void setShopStar(double shopStar) {
        this.shopStar = shopStar;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "AddComment{" +
                "shopcartDetailId='" + shopcartDetailId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                ", shopStar=" + shopStar +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
