package com.betterda.shopping.javabean;

/**
 * 我的推荐
 * Created by Administrator on 2017/2/5.
 */

public class TuiJian {
    private String heapCashback;
    private String number;

    public String getHeapCashback() {
        return heapCashback;
    }

    public void setHeapCashback(String heapCashback) {
        this.heapCashback = heapCashback;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TuiJian{" +
                "heapCashback='" + heapCashback + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
