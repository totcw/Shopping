package com.betterda.shopping.application;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.betterda.shopping.R;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.javabean.Bus;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by lyf
 */
public class MyApplication extends Application {

    private List<Activity> list;
    private static  MyApplication instance ;
  //  private List<Bus> busList;//参与结算的购物车数据


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (null == list) {
            list = new ArrayList<>();
        }
       /* if (null == busList) {
            busList = new ArrayList<>();
        }*/

        //百度地图
        SDKInitializer.initialize(getApplicationContext());
        //友盟
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx5baf9605cac65d91", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("2029403152", "5385e8aec38d793dcbe4c8c94b381007");
        PlatformConfig.setQQZone("101375552", "7aafc9a0d3c62dd92af2922d7e3eabda");
        Config.REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
        //对应平台没有安装的时候跳转转到应用商店下载
        Config.isJumptoAppStore = true;

        //极光推送
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush


    }



    /**
     * 将activity添加到容器中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (null != list) {
            list.add(activity);
        }

    }

    /**
     * 退出程序
     */
    public void exitProgress() {

        if (null != list) {

            for (Activity activity : list) {
                activity.finish();
            }
            //将容器清空
            list.clear();


        }


    }

    /**
     * 当activity销毁时调用该方法,防止内存泄漏
     *
     * @param activity
     */
    public void removeAcitivty(Activity activity) {
        if (null != list && activity != null) {

            list.remove(activity);
        }
    }


    public static MyApplication getInstance() {
        return instance;
    }

 /*   public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }*/



}
