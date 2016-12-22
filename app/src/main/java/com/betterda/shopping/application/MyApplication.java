package com.betterda.shopping.application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.baidu.mapapi.SDKInitializer;
import com.betterda.shopping.bus.model.Bus;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.utils.PermissionUtil;
import com.betterda.shopping.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf
 */
public class MyApplication extends Application {

    private List<Activity> list;
    private static  MyApplication instance ;
    private List<Bus> busList;//参与结算的购物车数据

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (null == list) {
            list = new ArrayList<>();
        }
        if (null == busList) {
            busList = new ArrayList<>();
        }

        //百度地图
        SDKInitializer.initialize(getApplicationContext());



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

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }



}
