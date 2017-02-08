package com.betterda.shopping.javabean;

/**
 * 商品评价
 * Created by Administrator on 2016/5/17.
 */
public class Comment {
    private String photo;  //图片路径
    private String nickName; //用户名
    private String time;//时间
    private String content;//内容
    private float commentstar;


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public float getCommentstar() {
        return commentstar;
    }

    public void setCommentstar(float commentstar) {
        this.commentstar = commentstar;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "photo='" + photo + '\'' +
                ", nickName='" + nickName + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", commentstar=" + commentstar +
                '}';
    }
}
