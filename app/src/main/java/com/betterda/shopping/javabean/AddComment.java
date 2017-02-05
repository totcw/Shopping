package com.betterda.shopping.javabean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 添加评论
 * Created by Administrator on 2017/1/5.
 */

public class AddComment implements Parcelable{
    private String productId;//商品的id
    private String shopName;
    private String shopUrl;
    private float commentstar;
    private String content;



    public AddComment() {
    }


    protected AddComment(Parcel in) {
        productId = in.readString();
        shopName = in.readString();
        shopUrl = in.readString();
        commentstar = in.readFloat();
        content = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(shopName);
        dest.writeString(shopUrl);
        dest.writeDouble(commentstar);
        dest.writeString(content);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public double getCommentstar() {
        return commentstar;
    }

    public void setCommentstar(float commentstar) {
        this.commentstar = commentstar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AddComment{" +
                "productId='" + productId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                ", commentstar=" + commentstar +
                ", content='" + content + '\'' +
                '}';
    }
}
