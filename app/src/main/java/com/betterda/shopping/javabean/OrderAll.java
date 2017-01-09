package com.betterda.shopping.javabean;


import com.betterda.shopping.bus.model.Bus;

import java.util.List;

/**
 * 订单列表
 * Created by Administrator on 2016/5/27.
 */
public class OrderAll {
    private String id;//订单号
    private String time; //订单时间
    private String type; //交易状态
    private List<Bus> list;
    private String state;//状态
    private float amount;//订单金额
    private float freight;//运费



    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Bus> getList() {
        return list;
    }

    public void setList(List<Bus> list) {
        this.list = list;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
