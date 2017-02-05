package com.betterda.shopping.javabean;

/**
 * Created by Administrator on 2016/12/26.
 */

public class UserInfo {
    private String account;
    private String token;
    private String nickName; //昵称
    private String photo;  //头像
    private String accountType;//是否是会员

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String


    toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", token='" + token + '\'' +
                ", nickName='" + nickName + '\'' +
                ", photo='" + photo + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
