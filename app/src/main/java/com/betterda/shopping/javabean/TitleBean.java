package com.betterda.shopping.javabean;

/**
 * 有分类title的 javabean
 * Created by Administrator on 2016/12/30.
 */

public class TitleBean<T>{
    private String tag;
    private T data;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
