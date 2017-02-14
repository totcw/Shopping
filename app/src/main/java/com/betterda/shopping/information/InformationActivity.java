package com.betterda.shopping.information;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.ShapeLoadingDialog;
import com.betterda.mylibrary.Utils.PermissionUtil;
import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.R;
import com.betterda.shopping.application.MyApplication;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.factory.LoadImageFactory;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetService;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.information.contract.InformationContract;
import com.betterda.shopping.information.presenter.InformationPresenterImpl;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.ImageTools;
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.NormalTopBar;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 个人资料
 * Created by Administrator on 2016/12/19.
 */

public class InformationActivity extends BaseActivity<InformationContract.Presenter> implements InformationContract.View {
    private String[] REQUEST_PERMISSIONS = new String[]{Manifest.permission.CAMERA};
    private static final int REQUEST_PERMISSION_CODE_TAKE_PIC = 9; //权限的请求码
    private static final int REQUEST_PERMISSION_SEETING = 8; //去设置界面的请求码

    @BindView(R.id.topbar_information)
    NormalTopBar mTopbarInformation;
    @BindView(R.id.sv_information_touxinag)
    ImageView mSvInformationTouxinag;
    @BindView(R.id.relative_information_touxiang)
    RelativeLayout mRelativeInformationTouxiang;
    @BindView(R.id.tv_information_name)
    TextView mTvInformationName;
    @BindView(R.id.linear_information_name)
    LinearLayout mLinearInformationName;

    private String goosimg;//存放照片的地址


    @Override
    protected InformationContract.Presenter onLoadPresenter() {
        return new InformationPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_information);
    }

    @Override
    public void init() {
        super.init();
        mTopbarInformation.setTitle("个人资料");
    }

    @OnClick({R.id.relative_information_touxiang, R.id.linear_information_name,R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;
            case R.id.relative_information_touxiang:
                showPhoto();
                break;
            case R.id.linear_information_name:
                UiUtils.startIntent(getmActivity(),NameActivity.class);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //先判断是否登录
        boolean islogin = CacheUtils.getBoolean(getmActivity(), Constants.Cache.ISLOGIN, false);
        if (islogin) {
            String number = getAccount();

            if (!TextUtils.isEmpty(number)) {

                //为了做到换号登录，数据还在，所以头像，昵称 要跟帐号绑定
                String touxiang = CacheUtils.getString(getmActivity(), number + Constants.Cache.TOUXIANG, "");
                if (!TextUtils.isEmpty(touxiang)) {
                    if (null != mSvInformationTouxinag) {

                        LoadImageFactory.getLoadImageInterface().loadImageFit(getmActivity(),touxiang,mSvInformationTouxinag);
                    }

                }
                String name = CacheUtils.getString(getmActivity(), number + Constants.Cache.NICKNAME, "");
                if (!TextUtils.isEmpty(name)) {
                    if (null != mTvInformationName) {

                        mTvInformationName.setText(name);
                    }
                }
            }
        } else {

            if (null != mSvInformationTouxinag) {
                //设置默认图片
                mSvInformationTouxinag.setImageResource(R.mipmap.zwt_zheng);
            }
            if (null != mTvInformationName) {

                mTvInformationName.setText("还没有昵称呢");
            }
        }


    }


    /**
     * 开启头像选择
     */
    private void showPhoto() {
        View view = LayoutInflater.from(this).inflate(R.layout.pp_choose_photo,
                null);
        TextView tv_cameral = (TextView) view.findViewById(R.id.tv_photo_cameral);
        TextView tv_photo = (TextView) view.findViewById(R.id.tv_photo_photo);
        TextView tv_cancell = (TextView) view.findViewById(R.id.tv_photo_cancell);
        tv_cameral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermiss();
                closePopupWindow();
            }
        });
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageTools.choosePicture(getmActivity(), Constants.PHOTOZOOM);
                closePopupWindow();
            }
        });
        tv_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePopupWindow();
            }
        });
        setUpPopupWindow(view);
    }

    /**
     * 请求拍照的权限
     */
    private void requestPermiss() {
        PermissionUtil.checkPermission(getmActivity(), mLinearInformationName, REQUEST_PERMISSIONS, REQUEST_PERMISSION_CODE_TAKE_PIC, new PermissionUtil.permissionInterface() {
            @Override
            public void success() {
                ImageTools.paizhao(getmActivity(), Constants.PHOTOHRAPH);
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
                ImageTools.paizhao(getmActivity(), Constants.PHOTOHRAPH);
            } else {
                //没有权限
                if (!PermissionUtil.shouldShowPermissions(this,permissions)) {//这个返回false 表示勾选了不再提示
                    UiUtils.showSnackBar(mSvInformationTouxinag, "请去设置界面设置权限", "去设置", new UiUtils.showSnackBarListener() {
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
                    UiUtils.showSnackBar(mSvInformationTouxinag, "请允许权限请求!");
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
        } else {
            result(requestCode, resultCode, data);
        }

    }

    /**
     * 解析拍照或者图片库的图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void result(int requestCode, int resultCode, Intent data) {
        resultSuccess(requestCode, resultCode, data);

        if (requestCode == 5 && resultCode == -1) { //resulrcode表示裁剪成功
            if (!ImageTools.checkSDCardAvailable()) {
                UiUtils.showToast(this, "内存卡错误,请检查您的内存卡");
                return;
            }
            // 防止内存溢出
            String path = Environment.getExternalStorageDirectory()
                    + "/image.png";

            Bitmap pic = ImageTools.scacleToBitmap(path, this);
            if (pic != null) {// 这个ImageView是拍照完成后显示图片

                setPhoto(pic);
            }
        }
    }

    private void resultSuccess(int requestCode, int resultCode, Intent data) {
        // 拍照
        if (requestCode == Constants.PHOTOHRAPH) {
            if (resultCode == RESULT_OK) {// 返回成功的时候
                ImageTools.cropImg(Constants.imageUri, this, 1, 1, 128, 128);
            } else if (resultCode == RESULT_CANCELED) {// 取消的时候
                UiUtils.showToast(this, "取消拍照");
            } else {
                // 失败的时候
                UiUtils.showToast(this, "拍照失败");
            }

        }
        // 读取相册缩放图片
        if (requestCode == Constants.PHOTOZOOM) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {

                    ImageTools.cropImg(uri, this, 1, 1, 128, 128);

                } else {
                    UiUtils.showToast(this, "图片选取失败");
                }
            }

        }
    }


    //设置照片
    private void setPhoto(final Bitmap pic) {

        // 将bitmap保存到本地
        ImageTools.savePhotoToSDCard(pic, Constants.PHOTOPATH,
                Constants.PHOTONAME);
        // 保存照片地址
        goosimg =  Constants.PHOTOPATH + Constants.PHOTONAME
                + ".png";

        //TODO 上传照片到服务器
        NetworkUtils.isNetWork(getmActivity(), mLinearInformationName, new NetworkUtils.SetDataInterface() {
            @Override
            public void getDataApi() {
                //封装普通的string字段
                RequestBody account = RequestBody.create(MediaType.parse("text/plain"), getAccount());
                RequestBody token = RequestBody.create(MediaType.parse("text/plain"), getToken());
                //封装文件
                RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"), new File(goosimg));

                //第一个参数是key,第二是文件名,如果没有文件名不会被当成文件
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", Constants.PHOTONAME
                        + ".png", file);

                final ShapeLoadingDialog dialog = UiUtils.createDialog(getmActivity(), "正在上传...");
                UiUtils.showDialog(getmActivity(),dialog);
                getRxManager().add(NetWork.getNetService()
                .getImgUpload(account,token,filePart)

                .compose(NetWork.handleResult(new BaseCallModel<String>()))
                .subscribe(new MyObserver<String>() {
                    @Override
                    protected void onSuccess(final String data, String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("图片上传路径success:"+data.toString());
                        }

                        getRxManager().add(NetWork.getNetService()
                                .getPwdUpdate(getAccount(),null,null,data)
                                .compose(NetWork.handleResult(new BaseCallModel<String>()))
                                .subscribe(new MyObserver<String>() {
                                    @Override
                                    protected void onSuccess(String data2, String resultMsg) {
                                        UiUtils.dissmissDialog(getmActivity(),dialog);
                                        UiUtils.showToast(getmActivity(),resultMsg);
                                        mSvInformationTouxinag.setImageBitmap(pic);
                                        CacheUtils.putString(getmActivity(), getAccount() + Constants.Cache.TOUXIANG, data);
                                    }

                                    @Override
                                    public void onFail(String resultMsg) {
                                        UiUtils.dissmissDialog(getmActivity(),dialog);
                                        UiUtils.showToast(getmActivity(),resultMsg);
                                    }

                                    @Override
                                    public void onExit() {
                                        UiUtils.dissmissDialog(getmActivity(),dialog);
                                        ExitToLogin();
                                    }
                                }));



                    }

                    @Override
                    public void onFail(String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("图片上传路径fail:"+resultMsg);
                        }
                        UiUtils.dissmissDialog(getmActivity(),dialog);
                        UiUtils.showToast(getmActivity(),resultMsg);
                    }

                    @Override
                    public void onExit() {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("token");
                        }
                        UiUtils.dissmissDialog(getmActivity(),dialog);
                        ExitToLogin();
                    }
                }));
            }
        });



    }

    @Override
    public void dismiss() {
        super.dismiss();
        UiUtils.backgroundAlpha(1.0f,getmActivity());
    }
}
