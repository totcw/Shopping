package com.betterda.shopping.sort.model;

/**
 * 类别
 * Created by Administrator on 2016/12/8.
 */

public class Sort {
    private String sortName; //商品名
    private boolean isSelect;

    public Sort(String sortName, boolean isSelect) {
        this.sortName = sortName;
        this.isSelect = isSelect;
    }

    public Sort() {
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
