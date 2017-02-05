package com.betterda.shopping.javabean;

/**
 * 商品评价
 * Created by Administrator on 2016/5/17.
 */
public class Comment {
    private String littlePicture;  //图片路径
    private String name; //用户名
    private String time;//时间
    private String content;//内容
    private float commentstar;


    public String getLittlePicture() {
        return littlePicture;
    }

    public void setLittlePicture(String littlePicture) {
        this.littlePicture = littlePicture;
    }

    public float getCommentstar() {
        return commentstar;
    }

    public void setCommentstar(float commentstar) {
        this.commentstar = commentstar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "littlePicture='" + littlePicture + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", commentstar=" + commentstar +
                '}';
    }
}
