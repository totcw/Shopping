package com.betterda.shopping.welcome;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.betterda.shopping.R;
import com.betterda.shopping.dialog.DeleteDialog;
import com.betterda.shopping.dialog.PermissionDialog;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.ShopBrand;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.GsonTools;
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.PermissionUtil;
import com.betterda.shopping.utils.RxManager;
import com.betterda.shopping.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * 欢迎页面
 * Created by Administrator on 2016/12/21.
 */

public class WelcomeActivity extends AppCompatActivity {

    private String[] REQUEST_PERMISSIONS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE};
    private HashMap<String,String> map;//管理权限的map
    private static final int REQUEST_PERMISSION_CODE_TAKE_PIC = 9; //权限的请求码
    private static final int REQUEST_PERMISSION_SEETING = 8; //去设置界面的请求码
    protected RxManager mRxManager;
    @BindView(R.id.iv_welcome)
    ImageView mIvWelcome;
    private PermissionDialog permissionDialog;//权限请求对话框


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initRxBus();
        startTopermission();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        startTopermission();
    }



    private void initRxBus() {
        mRxManager = new RxManager();
        mRxManager.on(WelcomeActivity.class.getSimpleName(), new Action1<Object>() {
            @Override
            public void call(Object o) {

                finish();
            }
        });

    }

    /**
     * 获取权限
     */
    private void startTopermission() {
        if (Build.VERSION.SDK_INT < 23) {
            //6.0一下直接去主页
            starToHome();

        } else {
            //6.0以上请求权限
            checkPermiss();
        }
    }

    //跳转到首页
    public void  starToHome(){
        if (Build.VERSION.SDK_INT < 23) {
            UiUtils.startIntent(this, MainActivity.class);
            finish();
        } else {
            UiUtils.startIntent(this, MainActivity.class);
        }
    }

    /**
     * 请求权限
     */
    private void checkPermiss() {
        PermissionUtil.checkPermission(this,  REQUEST_PERMISSIONS, new PermissionUtil.permissionInterface() {
            @Override
            public void success() {
                //请求成功
                getData();
            }

            @Override
            public void fail(final List<String> permissions) {

                if (map == null) {
                    map = new HashMap<>();
                    map.put("android.permission.ACCESS_COARSE_LOCATION", "位置信息");
                    map.put("android.permission.WRITE_EXTERNAL_STORAGE", "存储空间");
                    map.put("android.permission.CALL_PHONE", "打电话");

                }

                requestPermission(permissions.toArray(new String[permissions.size()]));


            }
        });
    }

    /**
     * 请求权限
     * @param permissions
     */
    private void requestPermission(final String[] permissions) {

        if (permissionDialog != null) {
            permissionDialog.dismiss();
        }
        //请求权限
        permissionDialog = new PermissionDialog(WelcomeActivity.this, new PermissionDialog.onConfirmListener() {
            @Override
            public void comfirm() {
                //请求权限
                PermissionUtil.requestContactsPermissions(WelcomeActivity.this,permissions,REQUEST_PERMISSION_CODE_TAKE_PIC);
            }

            @Override
            public void cancel() {
                WelcomeActivity.this.finish();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String permission : permissions) {
            if (map != null) {
                String s = map.get(permission);
                if (!TextUtils.isEmpty(s)) {
                    sb.append(s + " ");
                }
            }
        }

        permissionDialog.setTvcontent(sb.toString());
        permissionDialog.show();

    }

    /**
     * 请求权限2
     * @param permissions
     */
    private void requestPermission2(final String[] permissions) {
        DeleteDialog deleteDialog = new DeleteDialog(WelcomeActivity.this, new DeleteDialog.onConfirmListener() {
            @Override
            public void comfirm() {
                //去掉已经请求过的权限
                List<String> deniedPermissions = PermissionUtil.findDeniedPermissions(WelcomeActivity.this, permissions);
                //请求权限
                PermissionUtil.requestContactsPermissions(WelcomeActivity.this,deniedPermissions.toArray(new String[deniedPermissions.size()]),REQUEST_PERMISSION_CODE_TAKE_PIC);
            }

            @Override
            public void cancel() {
                WelcomeActivity.this.finish();
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String permission : permissions) {
            if (map != null) {
                String s = map.get(permission);
                if (!TextUtils.isEmpty(s)) {
                    sb.append(s + " ");
                }
            }
        }
        deleteDialog.setTvcontent("请允许"+sb+"权限请求");
        deleteDialog.show();
    }

    private void startToSetting() {
        DeleteDialog deleteDialog = new DeleteDialog(WelcomeActivity.this, new DeleteDialog.onConfirmListener() {
            @Override
            public void comfirm() {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, REQUEST_PERMISSION_SEETING);
            }

            @Override
            public void cancel() {
                WelcomeActivity.this.finish();
            }
        });


        deleteDialog.setTvcontent("去设置界面开启权限?");
        deleteDialog.show();
    }



    /**
     * 检测权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, final String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_CODE_TAKE_PIC) {
            if (PermissionUtil.verifyPermissions(grantResults)) {//有权限
                getData();

            } else {
                //没有权限
                if (!PermissionUtil.shouldShowPermissions(this, permissions)) {//这个返回false 表示勾选了不再提示

                    startToSetting();

                } else {
                    //表示没有权限 ,但是没勾选不再提示
                    for (String s : permissions) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(WelcomeActivity.this,
                                s)) {
                            //去掉已经允许的
                            if (map != null) {
                                map.remove(s);
                            }
                        }
                    }
                    requestPermission2(permissions);
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //如果是从设置界面返回,就继续判断权限
        if (requestCode == REQUEST_PERMISSION_SEETING) {
            checkPermiss();
        }

    }

    /**
     * 从服务器获取数据缓存
     */
    public void getData() {
        //判断是否有网络
        boolean netAvailable = NetworkUtils.isNetAvailable(this);
        if (netAvailable) {
            mRxManager.add(NetWork.getNetService()
            .getShopBrand()
            .compose(NetWork.handleResult(new BaseCallModel<List<ShopBrand>>()))
            .subscribe(new MyObserver<List<ShopBrand>>() {
                @Override
                protected void onSuccess(List<ShopBrand> data, String resultMsg) {
                    if (data != null) {
                        List<String> list = new ArrayList<String>();
                            for (ShopBrand s : data) {
                                if (null != s) {
                                    list.add(s.getBrand());
                                }
                            }
                        //缓存品牌
                        CacheUtils.putString(WelcomeActivity.this,Constants.Cache.PINPAI, GsonTools.getJsonListString(list));
                    }
                    starToHome();
                }

                @Override
                public void onFail(String resultMsg) {
                    System.out.println("品牌fail:"+resultMsg);
                    starToHome();
                }

                @Override
                public void onExit() {

                }
            }));
        } else {
            //没有网络直接去首页
            starToHome();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (map != null) {
            map.clear();
            map = null;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }
}
