package com.betterda.shopping.wallet.model;

/**
 * 明细
 * Created by Administrator on 2016/7/7.
 */
public class MingXi {
    private String source; //来源
    private String time; //时间
    private String sum; //余额
    private String money;//交易的钱

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "MingXi{" +
                "source='" + source + '\'' +
                ", time='" + time + '\'' +
                ", sum='" + sum + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
