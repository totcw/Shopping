package com.betterda.shopping.utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class Constants {

    public static final String CACHE_FILE_NAME = "betterdaShopping"; //缓存目录
    public static final String PHOTOPATH=Environment.getExternalStorageDirectory().getPath()+"/shopping/photo/";//存图片的路径
    public static final Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.png")); //保存拍照照片的uri
    public static String PHOTONAME="photo"; //存放照片的名字
    public static final int PHOTOZOOM = 1;// 相机选取
    public static final int PHOTOHRAPH = 2;// 相机拍照
    public static final String IMAGE_UNSPECIFIED = "image/*";

    public static class Url {

       public  final static String URL="";
        public static final String URL_REGISTER = " ";
        public static final String URL_LOGIN = "";
        public static final String URL_PWD_UPDATE = "";
    }

    public class WeiXin {
        public static final String APP_ID ="";
    }
}
