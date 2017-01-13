package com.betterda.shopping.javabean;



import java.util.List;

/**
 * 订单确认
 * Created by Administrator on 2016/6/27.
 */
public class OrderComfirm {
    private String account;//用户
    private String orderId;//订单id
    private String time;//订单时间
    private String name;//收货人姓名
    private String number;//收货人电话
    private String ares;//省市区
    private String address;//收货人详细地址
    private List<Bus> busList;//商品列表
    private float freight ;//运费  0表示免费
    private String  bill;//是否需要发票
    private String type;//配送方式  自提 快递
    private float money;//订单的实际支付金额  扣除代金卷
    private float voucher;//代金卷



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAres() {
        return ares;
    }

    public void setAres(String ares) {
        this.ares = ares;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getVoucher() {
        return voucher;
    }

    public void setVoucher(float voucher) {
        this.voucher = voucher;
    }

    @Override
    public String toString() {
        return "OrderComfirm{" +
                "account='" + account + '\'' +
                ", orderId='" + orderId + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", ares='" + ares + '\'' +
                ", address='" + address + '\'' +
                ", busList=" + busList +
                ", freight=" + freight +
                ", bill='" + bill + '\'' +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", voucher=" + voucher +
                '}';
    }
}
