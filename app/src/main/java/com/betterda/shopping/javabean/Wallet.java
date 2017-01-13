package com.betterda.shopping.javabean;

/**
 * 我的钱包
 * Created by Administrator on 2017/1/13.
 */

public class Wallet {
    private String cashWallet;//现金钱包
    private String consumptionWallet;//消费钱包

    public String getCashWallet() {
        return cashWallet;
    }

    public void setCashWallet(String cashWallet) {
        this.cashWallet = cashWallet;
    }

    public String getConsumptionWallet() {
        return consumptionWallet;
    }

    public void setConsumptionWallet(String consumptionWallet) {
        this.consumptionWallet = consumptionWallet;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "cashWallet='" + cashWallet + '\'' +
                ", consumptionWallet='" + consumptionWallet + '\'' +
                '}';
    }
}
