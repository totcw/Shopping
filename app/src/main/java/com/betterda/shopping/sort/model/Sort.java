package com.betterda.shopping.sort.model;

/**
 * 类别
 * Created by Administrator on 2016/12/8.
 */

public class Sort {
    private String catalogname; //商品类型品
    private boolean isSelect;

    public Sort(String catalogname, boolean isSelect) {
        this.catalogname = catalogname;
        this.isSelect = isSelect;
    }

    public Sort() {
    }

    public String getCatalogname() {
        return catalogname;
    }

    public void setCatalogname(String catalogname) {
        this.catalogname = catalogname;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "catalogname='" + catalogname + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }
}
