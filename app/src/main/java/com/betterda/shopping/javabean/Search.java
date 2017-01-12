package com.betterda.shopping.javabean;

import com.betterda.shopping.sort.model.Shopping;

import java.util.List;

/**
 * 搜索
 * Created by Administrator on 2017/1/12.
 */

public class Search {
    private String shopcartCount;
    private List<Shopping> list;

    public String getShopcartCount() {
        return shopcartCount;
    }

    public void setShopcartCount(String shopcartCount) {
        this.shopcartCount = shopcartCount;
    }

    public List<Shopping> getList() {
        return list;
    }

    public void setList(List<Shopping> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Search{" +
                "shopcartCount='" + shopcartCount + '\'' +
                ", list=" + list +
                '}';
    }
}
