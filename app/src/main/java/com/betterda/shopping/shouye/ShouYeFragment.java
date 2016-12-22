package com.betterda.shopping.shouye;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.Utils.StatusBarCompat;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.location.LocationActivity;
import com.betterda.shopping.shouye.contract.ShouYeContract;
import com.betterda.shopping.shouye.presenter.ShouYePresenterImpl;
import com.betterda.shopping.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ShouYeFragment extends BaseFragment<ShouYeContract.Presenter> implements ShouYeContract.View {


    @BindView(R.id.tv_shouye_city)
    TextView mTvShouyeCity; //定位城市
    @BindView(R.id.linear_location)
    LinearLayout mLinearLocation;//定位
    @BindView(R.id.frame_shouye_lunbotu)
    FrameLayout mFrameShouyeLunbotu; //轮播图
    @BindView(R.id.frame_shouye_second)
    FrameLayout mFrameShouyeSecond;//第二区域
    @BindView(R.id.frame_shouye_three)
    FrameLayout mFrameShouyeThree;//第三区域
    @BindView(R.id.loadpager_shouye)
    LoadingPager mLoadpagerShouye;
    private View mViewLunbotu; //轮播图
    private View mViewSecond;//第二区域的view
    private View mViewThree;
    private ViewPager mVpLunbotu;
    private LinearLayout mLinearFirst;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_shouye, null);
    }

    @Override
    protected ShouYeContract.Presenter onLoadPresenter() {
        return new ShouYePresenterImpl();
    }

    @Override
    public void initData() {
        super.initData();
        //加载轮播图
        initLunbotu();
        //加载第二区域
        initSecond();
        //加载第三区域
        initThree();
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//隐藏
        } else {
            StatusBarCompat.setStatusBar5(getmActivity(),R.color.backgroudyellow);

        }
    }


    @OnClick({R.id.linear_location, R.id.relative_shouye_message, R.id.iv_shouye_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_location:
                UiUtils.startIntent(getmActivity(), LocationActivity.class);
                break;
            case R.id.relative_shouye_message:
                break;
            case R.id.iv_shouye_search:
                break;
        }
    }

    /**
     * 初始化轮播图
     */
    private void initLunbotu() {
        mViewLunbotu = View.inflate(getmActivity(), R.layout.view_lunbotu, null);
        ImageView mIvFirst = (ImageView) mViewLunbotu.findViewById(R.id.iv_main_first);
        mVpLunbotu = (ViewPager) mViewLunbotu.findViewById(R.id.vpager_lunbotu);
        mLinearFirst = (LinearLayout) mViewLunbotu.findViewById(R.id.ll_points);
        mFrameShouyeLunbotu.addView(mViewLunbotu);

    }


    private void initSecond() {
        mViewSecond = View.inflate(getmActivity(), R.layout.view_second, null);
        mFrameShouyeSecond.addView(mViewSecond);
    }

    private void initThree() {
        mViewThree = View.inflate(getmActivity(), R.layout.view_three, null);
        mFrameShouyeThree.addView(mViewThree);
    }


    /**
     * 初始化轮播图的viewpager
     * @param adapter
     */
    public void initVpLunbotu(PagerAdapter adapter) {
        mVpLunbotu.setAdapter(adapter);
    }

    @Override
    public ViewGroup getLpoint() {
        return mLinearFirst;
    }

    @Override
    public ViewPager getViewPager() {
        return mVpLunbotu;
    }

}
