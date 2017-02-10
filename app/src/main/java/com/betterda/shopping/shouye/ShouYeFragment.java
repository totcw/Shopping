package com.betterda.shopping.shouye;

import android.content.Intent;
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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.Utils.StatusBarCompat;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.find.FindFragment;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.location.LocationActivity;
import com.betterda.shopping.message.MeassageActivity;
import com.betterda.shopping.search.SearchActivity;
import com.betterda.shopping.shouye.contract.ShouYeContract;
import com.betterda.shopping.shouye.presenter.ShouYePresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 * Created by Administrator on 2016/12/8.
 */

public class ShouYeFragment extends BaseFragment<ShouYeContract.Presenter> implements ShouYeContract.View, View.OnClickListener {


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

    private RelativeLayout mRelativeFirst, mRelativeSecond, mRelativeThree, mRelativeFour,
            mRelativeFive, mRelativeSix, mRelativeSeven, mRelativeEight;
    private LinearLayout mLinearThree1, mLinearThree2, mLinearThree3, mLinearThree4;


    /**
     * 定位功能
     */
    public LocationClient mLocationClient = null; //定位的类
    public BDLocationListener myListener = new MyLocationListener();


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
    public void initListenr() {
        super.initListenr();
        mRelativeFirst.setOnClickListener(this);
        mRelativeSecond.setOnClickListener(this);
        mRelativeThree.setOnClickListener(this);
        mRelativeFour.setOnClickListener(this);
        mRelativeFive.setOnClickListener(this);
        mRelativeSix.setOnClickListener(this);
        mRelativeSeven.setOnClickListener(this);
        mRelativeEight.setOnClickListener(this);
        mLinearThree1.setOnClickListener(this);
        mLinearThree2.setOnClickListener(this);
        mLinearThree3.setOnClickListener(this);
        mLinearThree4.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//隐藏
        } else {
            StatusBarCompat.setStatusBar5(getmActivity(), R.color.backgroudyellow);
            ((MainActivity)getmActivity()).getmBvMain().setVisibility(View.VISIBLE);
            startLocation();
        }
    }


    @OnClick({R.id.linear_location, R.id.relative_shouye_message, R.id.iv_shouye_search
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_location:

                Intent intent = new Intent(getmActivity(), LocationActivity.class);
                startActivityForResult(intent, 0);
                UiUtils.setOverdepengingIn(getmActivity());
                break;
            case R.id.relative_shouye_message:
                UtilMethod.isLogin(getmActivity(),MeassageActivity.class);

                break;
            case R.id.iv_shouye_search:
                UiUtils.startIntent(getmActivity(), SearchActivity.class);
                break;
            case R.id.relative_tjq://第二区域1
                UiUtils.showToast(getmActivity(), "1");
                break;
            case R.id.relative_zpjy://第二区域2
                UiUtils.showToast(getmActivity(), "2");
                break;
            case R.id.relative_qcjy://第二区域3
                UiUtils.showToast(getmActivity(), "3");
                break;
            case R.id.relative_xiuc://第二区域4
                UiUtils.showToast(getmActivity(), "4");
                break;
            case R.id.relative_xic://第二区域5
                UiUtils.showToast(getmActivity(), "5");
                break;
            case R.id.relative_jyz://第二区域6
                UiUtils.showToast(getmActivity(), "6");
                break;
            case R.id.relative_xhfw://第二区域7
                UiUtils.showToast(getmActivity(), "7");
                break;
            case R.id.relative_dj://第二区域8
                UiUtils.showToast(getmActivity(), "8");
                break;
            case R.id.linear_view_three1://第3区域1
                UiUtils.showToast(getmActivity(), "1");
                break;
            case R.id.linear_view_three2://第3区域2
                UiUtils.showToast(getmActivity(), "2");
                break;
            case R.id.linear_view_three3://第3区域3
                UiUtils.showToast(getmActivity(), "3");
                break;
            case R.id.linear_view_three4://第3区域4
                UiUtils.showToast(getmActivity(), "4");
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
        mRelativeFirst = (RelativeLayout) mViewSecond.findViewById(R.id.relative_tjq);
        mRelativeSecond = (RelativeLayout) mViewSecond.findViewById(R.id.relative_zpjy);
        mRelativeThree = (RelativeLayout) mViewSecond.findViewById(R.id.relative_qcjy);
        mRelativeFour = (RelativeLayout) mViewSecond.findViewById(R.id.relative_xiuc);
        mRelativeFive = (RelativeLayout) mViewSecond.findViewById(R.id.relative_xic);
        mRelativeSix = (RelativeLayout) mViewSecond.findViewById(R.id.relative_jyz);
        mRelativeSeven = (RelativeLayout) mViewSecond.findViewById(R.id.relative_xhfw);
        mRelativeEight = (RelativeLayout) mViewSecond.findViewById(R.id.relative_dj);
    }

    private void initThree() {
        mViewThree = View.inflate(getmActivity(), R.layout.view_three, null);
        mFrameShouyeThree.addView(mViewThree);
        mLinearThree1 = (LinearLayout) mViewThree.findViewById(R.id.linear_view_three1);
        mLinearThree2 = (LinearLayout) mViewThree.findViewById(R.id.linear_view_three2);
        mLinearThree3 = (LinearLayout) mViewThree.findViewById(R.id.linear_view_three3);
        mLinearThree4 = (LinearLayout) mViewThree.findViewById(R.id.linear_view_three4);
    }


    /**
     * 初始化轮播图的viewpager
     *
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (data != null) {
                String address = data.getStringExtra("address");
                mTvShouyeCity.setText(address);
            }
        }
    }

    /**
     * 开始定位相关
     */
    private void startLocation() {
        /**
         * 定位相关
         */
        mLocationClient = new LocationClient(getmActivity());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

        UtilMethod.initLocation(mLocationClient);
        //开启定位
        mLocationClient.start();
    }


    /**
     * 定位的回调方法
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location != null) {
                if (mTvShouyeCity != null) {
                    mTvShouyeCity.setText(location.getCity());
                }
            }

            //停止定位
            if (mLocationClient != null) {

                mLocationClient.stop();
            }

        }


    }
}
