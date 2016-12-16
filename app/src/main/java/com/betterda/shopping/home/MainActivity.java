package com.betterda.shopping.home;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.betterda.mylibrary.view.IndicatorView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.home.contract.HomeContract;
import com.betterda.shopping.home.presenter.HomePresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 */

public class MainActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {



    @BindView(R.id.idv_activity_main_shouye)
    IndicatorView mIdvShouye;
    @BindView(R.id.idv_activity_main_sort)
    IndicatorView mIdvSort;
    @BindView(R.id.idv_activity_main_find)
    IndicatorView mIdvFind;
    @BindView(R.id.idv_activity_main_my)
    IndicatorView mIdvMy;
    @BindView(R.id.linear_activity_main)
    LinearLayout mLinearActivityMain;
    @BindView(R.id.frame_activity_main)
    FrameLayout mFrameActivityMain;

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_main);

    }

    @Override
    protected HomeContract.Presenter onLoadPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public void init() {
        super.init();
        initIdv();
    }


    @OnClick({R.id.idv_activity_main_shouye, R.id.idv_activity_main_sort, R.id.idv_activity_main_find, R.id.idv_activity_main_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idv_activity_main_shouye:
                if (!getPresenter().checkPopWindow()) {
                    switchTo(mIdvShouye);
                    getPresenter().switchToFragment(R.id.idv_activity_main_shouye);
                }
                break;
            case R.id.idv_activity_main_sort:
                if (!getPresenter().checkPopWindow()) {
                    switchTo(mIdvSort);
                    getPresenter().switchToFragment(R.id.idv_activity_main_sort);
                }
                break;
            case R.id.idv_activity_main_find:
                if (!getPresenter().checkPopWindow()) {
                    switchTo(mIdvFind);
                    getPresenter().switchToFragment(R.id.idv_activity_main_find);
                }
                break;
            case R.id.idv_activity_main_my:
                if (!getPresenter().checkPopWindow()) {
                    switchTo(mIdvMy);
                    getPresenter().switchToFragment(R.id.idv_activity_main_my);
                }
                break;
        }
    }

    /**
     * 初始化底部导航
     */
    private void initIdv() {
        mIdvShouye.setIvBackground(R.mipmap.activity_main_shouye_normal,R.mipmap.activity_main_shouye_pressed);
        mIdvSort.setIvBackground(R.mipmap.activity_main_sort_normal,R.mipmap.activity_main_sort_pressed);
        mIdvFind.setIvBackground(R.mipmap.activity_main_find_normal,R.mipmap.activity_main_find_pressed);
        mIdvMy.setIvBackground(R.mipmap.activity_main_my_nomal,R.mipmap.activity_main_my_pressed);

        mIdvShouye.setLineBackground(getResources().getColor(R.color.activityMainNormal),getResources().getColor(R.color.activityMainPressed));
        mIdvSort.setLineBackground(getResources().getColor(R.color.activityMainNormal),getResources().getColor(R.color.activityMainPressed));
        mIdvFind.setLineBackground(getResources().getColor(R.color.activityMainNormal),getResources().getColor(R.color.activityMainPressed));
        mIdvMy.setLineBackground(getResources().getColor(R.color.activityMainNormal),getResources().getColor(R.color.activityMainPressed));

        mIdvShouye.setTitle("首页");
        mIdvSort.setTitle("分类");
        mIdvFind.setTitle("发现");
        mIdvMy.setTitle("我的");

        mIdvShouye.setTabSelected(true);
    }

    /**
     * 设置底部导航的选中状态
     * @param idv
     */
    public void switchTo(IndicatorView idv) {

            if (null != mIdvShouye && null != mIdvSort && null != mIdvFind && null != idv && mIdvMy != null) {

                mIdvShouye.setTabSelected(false);
                mIdvSort.setTabSelected(false);
                mIdvFind.setTabSelected(false);
                mIdvMy.setTabSelected(false);
                idv.setTabSelected(true);

            }

    }

    public IndicatorView getmIdvShouye() {
        return mIdvShouye;
    }
}
