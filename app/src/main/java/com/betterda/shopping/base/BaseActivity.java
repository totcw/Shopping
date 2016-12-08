package com.betterda.shopping.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.betterda.shopping.R;
import com.betterda.shopping.utils.RxManager;

import butterknife.ButterKnife;

/**
 * 基类
 * Created by Administrator on 2016/12/2.
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    protected P mPresenter;
    protected RxManager mRxManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRxManager = new RxManager();

        mPresenter = onLoadPresenter();
        if(getPresenter() != null) {
            getPresenter().attachView(this);
        }

        initView();
        ButterKnife.bind(this);
        initListener();
        init();

        if(getPresenter() != null) {
            //开始presenter的逻辑
            getPresenter().start();
        }
    }




    /**
     * 处理业务逻辑
     */
    public void init() {

    }

    /**
     * 设置监听
     */
    public void initListener() {

    }

    /**
     * 初始化view
     */
    public void initView() {

    }


    public P getPresenter() {
        return mPresenter;
    }


    /**
     * 加载presenter
     * @return
     */
    protected abstract P onLoadPresenter();

    /**
     * 关闭activity的方法
     */
    public void back() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          finishAfterTransition();
        } else {
            finish();
             overridePendingTransition(R.anim.activity_slide_finish_in,R.anim.activity_slide_finish_out);
        }
    }


    public Activity getmActivity() {
        return this;
    }

    public Context getContext() {
        return this;
    }

    public void log(String msg) {
        Log.i("BaseActivity", msg);
    }


    @Override
    protected void onDestroy() {

        if (getPresenter() != null) {
            //解除presenter和view的关联
            getPresenter().detachView();
            //调用presenter的销毁方法
            getPresenter().destroy();
        }
        mRxManager.clear();
        super.onDestroy();
    }

}
