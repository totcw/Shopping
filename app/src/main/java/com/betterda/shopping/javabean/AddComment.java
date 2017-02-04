package com.betterda.shopping.javabean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 添加评论
 * Created by Administrator on 2017/1/5.
 */

public class AddComment implements Parcelable{
    private String shopcartDetailId;//商品的id
    private String shopName;
    private String shopUrl;
    private double shopStar;
    private String commentContent;

    protected AddComment(Parcel in) {
        shopcartDetailId = in.readString();
        shopName = in.readString();
        shopUrl = in.readString();
        shopStar = in.readDouble();
        commentContent = in.readString();
    }

    public AddComment() {
    }

    public static final Creator<AddComment> CREATOR = new Creator<AddComment>() {
        @Override
        public AddComment createFromParcel(Parcel in) {
            return new AddComment(in);
        }

        @Override
        public AddComment[] newArray(int size) {
            return new AddComment[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(shopcartDetailId);
        dest.writeString(shopName);
        dest.writeString(shopUrl);
        dest.writeDouble(shopStar);
        dest.writeString(commentContent);
    }
}
