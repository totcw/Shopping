package com.betterda.shopping.wallet.model;

/**
 * 明细
 * Created by Administrator on 2016/7/7.
 */
public class MingXi {
    private String type; //类型
    private String time; //时间
    private String money; //余额
    private String money2;//交易的钱

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney2() {
        return money2;
    }

    public void setMoney2(String money2) {
        this.money2 = money2;
    }
}
