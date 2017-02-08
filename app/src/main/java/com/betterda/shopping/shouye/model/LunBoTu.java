package com.betterda.shopping.shouye.model;

/**
 * 轮播图的javabean
 * Created by Administrator on 2016/6/21.
 */
public class LunBoTu {
    private String url;
    private String imageId;
    private String title;

    public LunBoTu(String url, String imageId, String title) {
        this.url = url;
        this.imageId = imageId;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LunBoTu{" +
                "url='" + url + '\'' +
                ", imageId='" + imageId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
