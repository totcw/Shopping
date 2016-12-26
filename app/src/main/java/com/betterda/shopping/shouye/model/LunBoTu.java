package com.betterda.shopping.shouye.model;

/**
 * 轮播图的javabean
 * Created by Administrator on 2016/6/21.
 */
public class LunBoTu {
    private String url;
    private String id;

    public LunBoTu(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public LunBoTu() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
