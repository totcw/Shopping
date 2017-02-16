package com.betterda.shopping.javabean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 购物车商品的类
 * Created by Administrator on 2016/5/17.
 */
public class Bus implements Parcelable {
    private String shopcartDetailId;//商品的id
    private String productId;//商品的id
    private boolean isChosed; //是否选择
    private String littlePicture; //图片的地址
    private String productName;//商品名字
    private String salePrice;//售价
    private String totalCount;//选择的数量
    private String vipPrice;//会员价
    private String spec;//规格



    public Bus() {

    }


    protected Bus(Parcel in) {
        shopcartDetailId = in.readString();
        productId = in.readString();
        isChosed = in.readByte() != 0;
        littlePicture = in.readString();
        productName = in.readString();
        salePrice = in.readString();
        totalCount = in.readString();
        vipPrice = in.readString();
        spec = in.readString();
    }

    public static final Creator<Bus> CREATOR = new Creator<Bus>() {
        @Override
        public Bus createFromParcel(Parcel in) {
            return new Bus(in);
        }

        @Override
        public Bus[] newArray(int size) {
            return new Bus[size];
        }
    };

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shopcartDetailId);
        parcel.writeString(productId);
        parcel.writeByte((byte) (isChosed ? 1 : 0));
        parcel.writeString(littlePicture);
        parcel.writeString(productName);
        parcel.writeString(salePrice);
        parcel.writeString(totalCount);
        parcel.writeString(vipPrice);
        parcel.writeString(spec);
    }
}
