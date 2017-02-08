package com.betterda.shopping.javabean;

/**
 * 具体消息列表
 * Created by Administrator on 2017/1/4.
 */

public class MeassageContent {
    private String id;
    private String title;
    private String msgTime;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MeassageContent{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", msgTime='" + msgTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
