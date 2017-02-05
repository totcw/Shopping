package com.betterda.shopping.utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class Constants {

    public static final String CACHE_FILE_NAME = "betterdaShopping"; //缓存目录
    public static final String PHOTOPATH=Environment.getExternalStorageDirectory().getPath()+"/shopping/photo/";//存图片的路径
    public static final Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png")); //保存拍照照片的uri
    public static final String PAGESIZE = "10";//分页加载的个数
    public static String PHOTONAME="photo"; //存放照片的名字
    public static final int PHOTOZOOM = 1;// 相机选取
    public static final int PHOTOHRAPH = 2;// 相机拍照
    public static final String IMAGE_UNSPECIFIED = "image/*";


    public static class Cache{
        public final static String ISLOGIN = "isLogin";//登录状态
        public final static String ISMEMBER = "isMember";//是否是会员
        public final static String ACCOUNT = "account";//
        public final static String TOKEN = "token";//
        public final static String NICKNAME = "nickName";//昵称
        public final static String TOUXIANG = "touxiang";//头像
        public final static String PINPAI = "touxiang";//品牌
    }

    public static class Url {

       public  final static String URL="http://192.168.1.145:8080/WinePIN/";
        public static final String URL_REGISTER = "appAPI.do?api/account/user/register";
        public static final String URL_LOGIN = "appAPI.do?api/account/user/login";
        public static final String URL_PWD_UPDATE = "";
        public static final String URL_LUNBO = URL+"appAPI.do?api/indeximages/get";//轮播广告
        public static final String URL_ADD_BANK = "appAPI.do?api/account/bank/add";//添加银行卡
        public static final String URL_GET_BANK = "appAPI.do?api/account/bank/get";//获取银行卡列表
        public static final String URL_DEL_BANK = "appAPI.do?api/account/bank/del";//删除银行卡
        public static final String URL_ADD_ADDRESS = "appAPI.do?api/account/address/add";//添加配送地址
        public static final String URL_UPDATE_ADDRESS = "appAPI.do?api/account/address/update";//更新配送地址
        public static final String URL_DEL_ADDRESS = "appAPI.do?api/account/address/del";//删除配送地址
        public static final String URL_GET_ADDRESS = "appAPI.do?api/account/address/get";//获取配送地址列表
        public static final String URL_GET_SHOPLIST = "appAPI.do?api/account/product/get";//获取商品列表
        public static final String URL_GET_SHOPTYPE = "appAPI.do?api/account/catalog/get";//获取商品类型
        public static final String URL_GET_BRAND = "appAPI.do?api/account/brand/get";//获取品牌
        public static final String URL_GET_SHOPDETAIL = "appAPI.do?api/account/product/detail/get";//获取商品详情
        public static final String URL_GET_SHOPBRAND = "appAPI.do?api/account/brand/get";//获取商品品牌
        public static final String URL_ADD_SHOPCOMMENT = "appAPI.do?api/account/comment/add";//添加商品评论
        public static final String URL_GET_SHOPCOMMENT = "appAPI.do?api/account/comment/get";//获取商品评论
        public static final String URL_ADD_BUS = "appAPI.do?api/shopcart/product/add";//添加购物车
        public static final String URL_UPDATE_BUS = "appAPI.do?api/shopcart/product/update";//更新购物车
        public static final String URL_GET_BUS = "appAPI.do?api/shopcart/product/get";//获取购物车
        public static final String URL_DEL_BUS = "appAPI.do?api/shopcart/product/del";//删除购物车
        public static final String URL_DEFAULT_ADDRESS = "appAPI.do?api/default/address/get";//获取默认地址和消费钱包
        public static final String URL_BUS_COUNT = "appAPI.do?api/shopcart/count/get";//获取购物车数量
        public static final String URL_SEARCH = "appAPI.do?api/product/search";//搜索
        public static final String URL_SEARCH_REMEN = "appAPI.do?api/hotSearch/get";//获取热门搜索记录
        public static final String URL_SEARCH_JILU = "appAPI.do?api/account/search/get";//获取搜索记录
        public static final String URL_ADD_ORDER = "appAPI.do?api/account/order/add";//生成订单
        public static final String URL_GET_ORDER = "appAPI.do?api/account/order/get";//获取订单列表
        public static final String URL_GET_ORDERDETAIL = "appAPI.do?api/account/order/detail/get";//获取订单详情
        public static final String URL_CANCELORDER = "appAPI.do?api/account/order/cancel";//取消订单
        public static final String URL_GET_GOODS = "appAPI.do?api/account/order/receive";//立即收货
        public static final String URL_GET_CASH = "appAPI.do?api/account/cash/withdraw";//提现
        public static final String URL_GET_LOCATION = "appAPI.do?api/aroundShops/get";//获取附近商家信息
        public static final String URL_UPLOAD = "appAPI.do?api/android/img/upload";//上传图片
        public static final String URL_GET_WALLET = "appAPI.do?api/account/wallet/get";//我的钱包
        public static final String URL_GET_WALLETMINGXI = "appAPI.do?api/account/wallet/detail/get";//钱包的交易明细
        public static final String URL_GET_ZITIMA = "appAPI.do?api/account/order/barcode/get";//获取自提码


    }

    public class WeiXin {
        public static final String APP_ID ="";
    }
}
