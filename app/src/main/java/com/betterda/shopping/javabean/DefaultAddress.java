package com.betterda.shopping.javabean;

/**
 * 默认地址和消费钱包
 * Created by Administrator on 2017/1/12.
 */

public class DefaultAddress {
    private String consigneeName;//姓名
    private String mobilePhone;//手机号
    private String address;//省丶市
    private String detailAddress;//详细地址
    private String consumption;//消费钱包

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "DefaultAddress{" +
                "consigneeName='" + consigneeName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", consumption='" + consumption + '\'' +
                '}';
    }
}
