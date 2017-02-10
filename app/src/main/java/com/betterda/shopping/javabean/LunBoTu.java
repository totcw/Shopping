package com.betterda.shopping.javabean;

/**
 * 轮播图的javabean
 * Created by Administrator on 2016/6/21.
 */
public class LunBoTu {
    private String picture;
    private String imgId;
    private String title;

    public LunBoTu(String picture, String imgId, String title) {
        this.picture = picture;
        this.imgId = imgId;
        this.title = title;
    }

    public String getpicture() {
        return picture;
    }

    public void setpicture(String picture) {
        this.picture = picture;
    }

    public String getimgId() {
        return imgId;
    }

    public void setimgId(String imgId) {
        this.imgId = imgId;
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
                "picture='" + picture + '\'' +
                ", imgId='" + imgId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
