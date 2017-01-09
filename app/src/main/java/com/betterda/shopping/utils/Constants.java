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
        public final static String ACCOUNT = "account";//
        public final static String TOKEN = "token";//
        public final static String NICKNAME = "nickName";//昵称
        public final static String TOUXIANG = "touxiang";//头像
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
    }

    public class WeiXin {
        public static final String APP_ID ="";
    }
}
