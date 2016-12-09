package com.betterda.shopping.sort.model;

/**
 * 筛选的条件的javabean
 * Created by Administrator on 2016/12/9.
 */

public class Type {
    private String name;
    private boolean isSelect;//是否选中

    public Type(String name) {
        this.name = name;
    }
    public Type(String name,boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
