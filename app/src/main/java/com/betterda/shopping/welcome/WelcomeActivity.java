package com.betterda.shopping.welcome;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.ImageView;


import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.utils.PermissionUtil;
import com.betterda.shopping.utils.UiUtils;

import butterknife.BindView;

/**
 * 欢迎页面
 * Created by Administrator on 2016/12/21.
 */

public class WelcomeActivity extends BaseActivity {

    private String[] REQUEST_PERMISSIONS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_PERMISSION_CODE_TAKE_PIC = 9; //权限的请求码
    private static final int REQUEST_PERMISSION_SEETING = 8; //去设置界面的请求码

    @BindView(R.id.iv_welcome)
    ImageView mIvWelcome;

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT < 23) {
            //请求成功
           // UiUtils.startIntent(getmActivity(), MainActivity.class);
           // finish();
        } else {
            System.out.println("dd");
            requestPermiss();
        }
    }


    /**
     * 请求拍照的权限
     */
    private void requestPermiss() {
        PermissionUtil.checkPermission(getmActivity(), mIvWelcome, REQUEST_PERMISSIONS, REQUEST_PERMISSION_CODE_TAKE_PIC, new PermissionUtil.permissionInterface() {
            @Override
            public void success() {
                //请求成功
                UiUtils.startIntent(getmActivity(), MainActivity.class);
               finish();
            }
        });
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
                //TODO 有权限
                UiUtils.startIntent(getmActivity(), MainActivity.class);
                finish();
            } else {
                //没有权限
                if (!PermissionUtil.shouldShowPermissions(this, permissions)) {//这个返回false 表示勾选了不再提示
                    UiUtils.showSnackBar(mIvWelcome, "请去设置界面设置权限", "去设置", new UiUtils.showSnackBarListener() {
                        @Override
                        public void doSnack() {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, REQUEST_PERMISSION_SEETING);
                        }
                    });

                } else {
                    //表示没有权限 ,但是没勾选不再提示
                    UiUtils.showSnackBar(mIvWelcome, "请允许权限请求!");
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
            requestPermiss();
        }

    }


}
