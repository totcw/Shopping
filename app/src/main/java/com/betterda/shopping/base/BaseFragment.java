package com.betterda.shopping.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.login.LoginActivity;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.RxManager;
import com.betterda.shopping.utils.UiUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/5.
 */

public abstract class BaseFragment <P extends IPresenter> extends Fragment implements IView {
    private Activity mActivity;
    protected P mPresenter;
    protected RxManager mRxManager;
    private PopupWindow popupWindow;
    private AlertDialog.Builder builder;
    private boolean isDismiss;//token 失效对话框 是否已经显示
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity; //在这里获取到acitiviy,防止内存不够,activity被销毁,调用getactivity方法时返回null
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView(inflater);
        ButterKnife.bind(this, view);
        mRxManager = new RxManager();
        mPresenter = onLoadPresenter();
        if(getPresenter() != null) {
            getPresenter().attachView(this);
        }
        return view;
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        initData();
        initListenr();
        if(getPresenter() != null) {
            //开始presenter的逻辑
            getPresenter().start();
        }
        setLoadpagerBackgroud();
    }


    protected abstract P onLoadPresenter();

    /**
     * 设置监听
     */
    public void initListenr() {

    }


    /**
     * 子类必须实现此方法, 返回一个View对象, 作为当前Fragment的布局来展示.
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 如果子类需要初始化自己的数据, 把此方法给覆盖.
     */
    public void initData() {

    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public Activity getmActivity() {
        return mActivity;
    }


    public void log(String msg) {
        Log.i("BaseFragment", msg);
    }
    /**
     * 获取帐号
     * @return
     */
    public String getAccount() {
        return CacheUtils.getString(getmActivity(), Constants.Cache.ACCOUNT, "");
    }

    /**
     * 获取token
     * @return
     */
    public String getToken() {
        return  CacheUtils.getString(getmActivity(), getAccount()+Constants.Cache.TOKEN, "");
    }

    @Override
    public RxManager getRxManager() {
        return mRxManager;
    }

    public LoadingPager getLodapger(){
        return null;
    };

    public void setLoadpagerBackgroud() {
        if (getLodapger() != null) {
            getLodapger().setEmptyBackground(R.mipmap.load_empty);
            getLodapger().setErrorBackground(R.mipmap.load_error);
            getLodapger().setLoadBackground(R.drawable.loadinganim);
        }
    }

    /**
     * 强制跳转到登录界面
     */
    public void ExitToLogin() {
        if (!isDismiss) {
            isDismiss = true;
            if (builder == null) {
                builder = new AlertDialog.Builder(getmActivity());
            }
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    isDismiss = false;
                    builder = null;
                }
            });

            builder.setTitle("温馨提示")
                    .setMessage("您的帐号已在别处登录,请重新登录")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            CacheUtils.putBoolean(getmActivity(), Constants.Cache.ISLOGIN, false);
                            CacheUtils.putString(getmActivity(), Constants.Cache.ACCOUNT, "");
                            UiUtils.startIntent(getmActivity(), LoginActivity.class);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }


    }


    /**
     * 初始化并显示PopupWindow
     *
     * @param view     要显示的界面
     * @param showView 显示在哪个控件下面
     * @param x 宽度
     */
    public void setUpPopupWindow(View view, View showView,int x,int y) {
        // 如果activity不在运行 就返回
        if (getActivity().isFinishing()) {
            return;
        }
        if (null == showView) {
            popupWindow = new PopupWindow(view, -1, -2);
        } else {
            if (x != 0 && y != 0) {

                popupWindow = new PopupWindow(view, x, y);
            } else {
                popupWindow = new PopupWindow(view, -1, -1);
            }
        }

        // 设置点到外面可以取消,下面这2句要一起
        if (null == showView) {
            popupWindow.setOutsideTouchable(true);
        }
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置为true 会拦截事件,pop外部的控件无法获取到事件
        if (null == showView) {
            popupWindow.setFocusable(true);
        } else {
            popupWindow.setFocusable(true);
        }
        if (null == showView) {
            UiUtils.backgroundAlpha(0.7f, getmActivity());

        }
        //设置可以触摸
        popupWindow.setTouchable(true);

        if (popupWindow != null) {
            if (!popupWindow.isShowing()) {
                if (null == showView) {
                    //设置动画
                    popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                    popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                } else {
                    popupWindow.setAnimationStyle(R.style.popupwindow_anim);
                    popupWindow.showAsDropDown(showView);
                }

            }
        }
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {

                dismiss();
                popupWindow = null;
            }
        });




    }

    /**
     * popupwindow消失回调方法
     */
    public void dismiss() {

    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    /**
     * 关闭popupwindow
     */
    public void closePopupWindow() {
        if (null != popupWindow && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    @Override
    public void onDestroyView() {
        if (getPresenter() != null) {
            //解除presenter和view的关联
            getPresenter().detachView();
            //调用presenter的销毁方法
            getPresenter().destroy();
        }
        mRxManager.clear();
        closePopupWindow();
        super.onDestroyView();
    }
}
