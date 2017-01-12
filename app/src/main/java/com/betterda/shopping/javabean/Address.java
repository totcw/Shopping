package com.betterda.shopping.javabean;

/**
 * 收货地址
 * Created by Administrator on 2016/5/20.
 */
public class Address {
    private String addressId;
    private String account;
    private String token;
    private String consigneeName;//姓名
    private String mobilePhone;//手机号
    private String address;//省丶市
    private String detailAddress;//详细地址
    private boolean isDefault;//是否是默认

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", account='" + account + '\'' +
                ", token='" + token + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", tellPhone='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
