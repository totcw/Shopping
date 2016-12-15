package com.betterda.shopping.productdetails.model;

/**
 * 分享
 * Created by Administrator on 2016/12/15.
 */

public class Share {
    private String name;
    private int resId;

    public Share(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public Share() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
