package com.betterda.shopping.javabean;

/**
 * 添加评论
 * Created by Administrator on 2017/1/5.
 */

public class AddComment {
    private String shopId;
    private String shopName;
    private String shopUrl;
    private double shopStar;
    private String commentContent;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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
}
