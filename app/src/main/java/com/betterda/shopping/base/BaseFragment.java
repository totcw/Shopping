package com.betterda.shopping.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betterda.shopping.utils.RxManager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/5.
 */

public abstract class BaseFragment <P extends IPresenter> extends Fragment implements IView {
    private Activity mActivity;
    protected P mPresenter;
    protected RxManager mRxManager;

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

        initListenr();
        initData();
        if(getPresenter() != null) {
            //开始presenter的逻辑
            getPresenter().start();
        }
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

    @Override
    public void onDestroyView() {
        if (getPresenter() != null) {
            //解除presenter和view的关联
            getPresenter().detachView();
            //调用presenter的销毁方法
            getPresenter().destroy();
        }
        mRxManager.clear();
        super.onDestroyView();
    }
}
