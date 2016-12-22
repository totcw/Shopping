package com.betterda.shopping.location;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;

import com.betterda.shopping.address.AddAddressActivity;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.location.contract.LocationContract;
import com.betterda.shopping.location.model.Address;
import com.betterda.shopping.location.presenter.LocationPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 位置信息
 * Created by Administrator on 2016/12/22.
 */

public class LocationActivity extends BaseActivity<LocationContract.Presenter> implements LocationContract.View, OnGetSuggestionResultListener {

    // SuggestionSearch建议查询类
    private SuggestionSearch mSuggestionSearch;
    @BindView(R.id.relative_location_title)
    RelativeLayout mRelativeLocationTitle;//标题栏
    @BindView(R.id.relative_commonaddress)
    RelativeLayout mRelativeCommonaddress;//常用地址
    @BindView(R.id.relative_location_delete)
    RelativeLayout mRelativeLocationDelete;//标题栏的删除按钮
    @BindView(R.id.linear_location)
    LinearLayout mLinearLocation;
    @BindView(R.id.linear_search)
    LinearLayout mLinearSearch;
    @BindView(R.id.linear_location_current)
    LinearLayout mLinearLocationCurrent;//当前位置
    @BindView(R.id.tv_loaction_address)
    TextView mTvAddress;//当前地址
    @BindView(R.id.tv_loaction_dingwei)
    TextView mTvDingwei;//定位按钮
     @BindView(R.id.tv__search_cancel)
    TextView mTvCancel;//取消
    @BindView(R.id.et_search)
    EditText et_search;//搜索的输入框
    @BindView(R.id.loadpager_location)
    LoadingPager mLoadpagerLocation;
    @BindView(R.id.loadpager_pp_choseaddress)
    LoadingPager mLoadpagerChoseAddress;//搜索时的loagpager
    @BindView(R.id.rv_pp_choseaddress)
    RecyclerView mRvChoseAddress;
    @BindView(R.id.rv_location)
    RecyclerView mRvLocation;
    @BindView(R.id.iv_layout_search_delete)
    ImageView iv_delete;//搜索框的删除按钮
    @BindView(R.id.frame_location_rv)
    FrameLayout frame_location_rv;//常用地址层
    @BindView(R.id.frame_location_show)
    FrameLayout frame_location_show;//搜索的阴影层
    @BindView(R.id.frame_pp_choseaddress)
    FrameLayout  frame_pp_choseaddress; //搜藏层

    private int currententValue;//记录当前的值
    private boolean isFinishAnim;//是否完成过动画
    private String city;//记录当前定位城市
    private List<Address> list;
    private List<com.betterda.shopping.address.model.Address> listLocation;//常用地址的容器
    private CommonAdapter<Address> adapter;//建议查询的适配器
    private CommonAdapter<com.betterda.shopping.address.model.Address> adapterLocation;
    /**
     * 定位功能
     */
    public LocationClient mLocationClient; //定位的类
    public BDLocationListener myListener = new MyLocationListener();

    private int page = 1;
    private double longitude;//经度
    private double dimension;//纬度


    @Override
    protected LocationContract.Presenter onLoadPresenter() {
        return new LocationPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_location);
    }

    @Override
    public void init() {
        super.init();
        mLinearSearch.setBackgroundColor(getResources().getColor(R.color.backgroudyellow));

        initSuggestionAndLocation();
        list = new ArrayList<>();
        listLocation = new ArrayList<>();

        setPpRecycleview();
        setLocationRecycleview();

        addTextChange(et_search, iv_delete, frame_pp_choseaddress);

        getData();
    }



    @OnClick({R.id.linear_location_current,R.id.tv__search_cancel ,R.id.frame_location_show
    ,R.id.iv_layout_search_delete,R.id.et_search,R.id.relative_location_delete,
            R.id.tv_loaction_dingwei,R.id.tv_location_choseaddress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_location_current://点击当前地址
                if (mTvAddress != null) {
                    String address = (String) mTvAddress.getText();
                    if (TextUtils.isEmpty(address )|| "定位失败".equals(address)) {
                        return;
                    }
                }
                close(city);
                break;
            case R.id.tv__search_cancel://取消
                closeAnim();
                break;
            case R.id.frame_location_show://阴影层的点击事件
                mTvCancel.setVisibility(View.GONE);
                closeAnim();
                break;
            case R.id.iv_layout_search_delete://删除图标
                if (et_search != null) {
                    et_search.setText("");
                }
                break;
            case R.id.et_search: //输入框
                click();
                break;

            case R.id.relative_location_delete://标题栏的删除
                back();
                break;
            case R.id.tv_loaction_dingwei://重新定位
                if (mTvDingwei != null) {
                    mTvDingwei.setSelected(true);
                }
                if (mLocationClient != null) {
                    mLocationClient.start();
                }
                break;
            case R.id.tv_location_choseaddress://新增收货地址
                Intent intent = new Intent(getmActivity(), AddAddressActivity.class);
                intent.putExtra("isAdd", true);
                UiUtils.startIntent(getmActivity(), intent);
                break;
        }
    }

    /**
     * 初始化建议查询和定位
     */
    private void initSuggestionAndLocation() {
        // 实例化建议查询类
        mSuggestionSearch = SuggestionSearch.newInstance();
        // 注册建议查询事件监听
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        /**
         * 定位相关
         */
        mLocationClient = new LocationClient(this);//声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        UtilMethod.initLocation(mLocationClient);
        //开启定位
        mLocationClient.start();
    }

    /**
     * 建议查询需要实现的类
     * @param res
     */
    @Override
    public void onGetSuggestionResult(SuggestionResult res) {

        if (res == null || res.getAllSuggestions() == null) {
            if (mLoadpagerChoseAddress != null) {
                mLoadpagerChoseAddress.hide();
            }
            return;
        }

        list.clear();
        for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
            if (info.key != null) {
                if (info.pt != null) {
                    Address address = new Address();
                    address.setKey(info.city);
                    address.setAddress(info.city + info.district + info.key);
                    list.add(address);
                }


            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        if (mLoadpagerChoseAddress != null) {
            mLoadpagerChoseAddress.hide();
        }
    }

    /**
     * 定位的回调方法
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            if (null != location) {

                if (mTvAddress != null && location.getCity() != null) {
                    mTvAddress.setText(location.getProvince() + location.getCity() + location.getDistrict() + location.getStreet() + location.getStreetNumber());
                    city = location.getCity();
                    longitude = location.getLongitude();
                    dimension = location.getLatitude();
                } else {
                    mTvAddress.setText("定位失败");
                }

            }

            if (mTvDingwei != null) {
                mTvDingwei.setSelected(false);
            }

            //停止定位
            if (mLocationClient != null) {

                mLocationClient.stop();
            }

        }
    }


    /**
     * 初始化建议查询
     */
    private void setPpRecycleview() {
        adapter = new CommonAdapter<Address>(this, R.layout.item_pp_choseaddress, list) {
            @Override
            public void convert(ViewHolder holder, final Address address) {
                if (address != null) {
                    holder.setText(R.id.tv_item_pp_choseaddress2, address.getAddress());
                    holder.setText(R.id.tv_item_pp_choseaddress, address.getKey());
                    holder.setOnClickListener(R.id.linear_pp_choseaddress_title, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                longitude = Double.parseDouble(address.getLongitude());
                                dimension = Double.parseDouble(address.getLatitude());
                            } catch (Exception e) {

                            }
                            close(address.getKey());
                           // uploadAddress(address.getLongitude(), address.getLatitude(), address.getKey(), address.getAddress());
                        }
                    });
                }

            }
        };

        mRvChoseAddress.setLayoutManager(new LinearLayoutManager(LocationActivity.this));
        mRvChoseAddress.setAdapter(adapter);
    }

    /**
     * 初始化常用地址的rv
     */
    private void setLocationRecycleview() {
        adapterLocation = new CommonAdapter<com.betterda.shopping.address.model.Address>(this, R.layout.item_recycleview_address, listLocation) {


            @Override
            public void convert(ViewHolder holder, final com.betterda.shopping.address.model.Address address) {
                if (null != address) {
                    holder.setVisible(R.id.relative_item_address, false);

                }
            }
        };

        mRvLocation.setLayoutManager(new LinearLayoutManager(this));
        mRvLocation.setAdapter(adapterLocation);
    }



    /**
     * 给editview设置监听
     *
     * @param etPpchoseAddress
     * @param imageView
     * @param view
     */
    private void addTextChange(EditText etPpchoseAddress, final ImageView imageView, final View view) {
        etPpchoseAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() <= 0) {
                    if (imageView != null) {
                        imageView.setVisibility(View.GONE);
                    }
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }

                    return;
                }
                if (imageView != null) {
                    imageView.setVisibility(View.VISIBLE);
                }
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }

                if (mLoadpagerChoseAddress != null) {
                    mLoadpagerChoseAddress.setLoadVisable();
                }
                //开启建议查询
                mSuggestionSearch
                        .requestSuggestion((new SuggestionSearchOption())
                                .keyword(s.toString()).city(""));


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    /**
     * 关闭选择地址界面
     */
    public void close(String city) {
        if (TextUtils.isEmpty(city)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("address", city);
        intent.putExtra("longitude",longitude);
        intent.putExtra("dimension",dimension);
        setResult(0, intent);
        back();
    }
    /**
     * 开启动画
     */
    private void showAmin() {
        //设置开启过动画
        isFinishAnim = true;
        openInput();
        frame_location_show.setVisibility(View.VISIBLE);
        startAnim();

    }

    /**
     * 开始动画
     */

    private void startAnim() {
        currententValue = mRelativeLocationTitle.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(currententValue, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                //让界面移动
                if (mLinearLocation != null) {
                    mLinearLocation.scrollBy(0, currententValue - animatedValue);
                }
                currententValue = animatedValue;
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                mLinearSearch.setClickable(true);
                //获取屏幕的高度
                int height = UtilMethod.getHeight(LocationActivity.this);
                setHeight(height + mRelativeLocationTitle.getHeight());

                //重新设置搜索时的高度
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.height = height - mLinearSearch.getHeight() - UtilMethod.statusHeight(LocationActivity.this) - UtilMethod.getNavigationBarHeight(LocationActivity.this);
                frame_pp_choseaddress.setLayoutParams(layoutParams);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.setDuration(200).start();
    }

    /**
     * 重新设置整个布局的高度
     */
    private void setHeight(int height) {

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (height > 0) {
            layoutParams.height = height;
        }
        mLinearLocation.setLayoutParams(layoutParams);
    }


    /**
     * 点击输入框
     */
    private void click() {
        //设置不可以点击
        mLinearSearch.setClickable(false);
        if (!isFinishAnim) {
            //没有开启过动画
            mTvCancel.setVisibility(View.VISIBLE);
            showAmin();

        }
    }
    /**
     * 打开软键盘
     */
    private void openInput() {
        //打开软键盘
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     */
    private void closeInput() {
        //打开软键盘
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }


    /**
     * 关闭动画
     */
    private void closeAnim() {
        mLinearSearch.setClickable(false);
        et_search.setText("");
        mTvCancel.setVisibility(View.GONE);
        setHeight(0);
        closeInput();
        if (mRelativeLocationTitle == null) {
            return;
        }
        currententValue = -mRelativeLocationTitle.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(currententValue, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                if (currententValue - animatedValue < 0) {
                    if (mLinearLocation != null) {

                        mLinearLocation.scrollBy(0, currententValue - animatedValue);
                    }
                }
                currententValue = animatedValue;
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mLinearSearch.setClickable(true);
                isFinishAnim = false;
                frame_location_show.setVisibility(View.INVISIBLE);
                frame_pp_choseaddress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setDuration(200).start();


    }

    private void getData() {
        for (int i=0;i<3;i++) {
            listLocation.add(new com.betterda.shopping.address.model.Address());
        }
        adapterLocation.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {

        if (isFinishAnim) {
            closeAnim();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSuggestionSearch.destroy();
    }


}
