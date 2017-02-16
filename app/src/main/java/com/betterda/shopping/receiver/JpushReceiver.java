package com.betterda.shopping.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.betterda.shopping.message.MeassageActivity;
import com.betterda.shopping.utils.UiUtils;

import cn.jpush.android.api.JPushInterface;


/**
 * 极光推送的 接收消息的广播
 * Created by Administrator on 2016/12/1.
 */

public class JpushReceiver extends BroadcastReceiver {
    private String TAG=JpushReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("接受到");
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());

     if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        }  else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
         if (context != null) {
             Intent i = new Intent(context, MeassageActivity.class);  //自定义打开的界面
             i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(i);
         }

        }

    }
}
