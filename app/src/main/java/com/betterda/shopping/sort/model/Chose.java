package com.betterda.shopping.sort.model;

/**
 * 筛选的javabean
 * Created by Administrator on 2016/12/9.
 */

public class Chose {
    private String type; //类型 品牌,价格
    private String name; //选中的名字

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
