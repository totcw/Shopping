package com.betterda.shopping.javabean;

/**
 * 自提码
 * Created by Administrator on 2017/1/13.
 */

public class ZItiMa {
    private String barCode;
    private String time;
    private String orderNum;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "ZItiMa{" +
                "barCode='" + barCode + '\'' +
                ", time='" + time + '\'' +
                ", orderNum='" + orderNum + '\'' +
                '}';
    }
}
