package com.betterda.shopping.order;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.betterda.shopping.R;
import com.betterda.shopping.adapter.MyAdapter;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.order.contract.OrderContract;
import com.betterda.shopping.order.fragment.AllFragment;
import com.betterda.shopping.order.fragment.BringFragment;
import com.betterda.shopping.order.fragment.CommentFragment;
import com.betterda.shopping.order.fragment.PayFragment;
import com.betterda.shopping.order.fragment.SendFragment;
import com.betterda.shopping.order.fragment.TakeFragment;
import com.betterda.shopping.order.presenter.OrderPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;
import com.betterda.shopping.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的订单
 * Created by Administrator on 2016/12/19.
 */

public class OrderActivity extends BaseActivity<OrderContract.Presenter> implements OrderContract.View {
    @BindView(R.id.topbar_order)
    NormalTopBar mTopbarOrder;
    @BindView(R.id.order_indicator)
    ViewPagerIndicator mOrderIndicator;
    @BindView(R.id.vp_order)
    ViewPager mVpOrder;

    private List<String> mDatas; //viewpager指示器的数据
    private List<Fragment> mFragmentList;
    private Fragment mAllFragment,mPayFragment,mSendFragment,mBringFragment,mTakeFragment,mCommentFragment;

    @Override
    protected OrderContract.Presenter onLoadPresenter() {
        return new OrderPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_order);
    }

    @Override
    public void init() {
        super.init();
        mTopbarOrder.setTitle("我的订单");
        initFragment();
        initIndicator();

    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mAllFragment = new AllFragment();
        mPayFragment = new PayFragment();
        mSendFragment = new SendFragment();
        mBringFragment = new BringFragment();
        mTakeFragment = new TakeFragment();
        mCommentFragment = new CommentFragment();
        mFragmentList.add(mAllFragment);
        mFragmentList.add(mPayFragment);
        mFragmentList.add(mSendFragment);
        mFragmentList.add(mBringFragment);
        mFragmentList.add(mTakeFragment);
        mFragmentList.add(mCommentFragment);
    }

    private void initIndicator() {
        mDatas = new ArrayList<>();
        mDatas.add("全部");
        mDatas.add("待付款");
        mDatas.add("待发货");
        mDatas.add("待收货");
        mDatas.add("待提货");
        mDatas.add("待评价");
        mOrderIndicator.setTabItemTitles(mDatas);
        mVpOrder.setAdapter(new MyAdapter(getSupportFragmentManager(),mFragmentList));
        int item = getIntent().getIntExtra("item", 0);
        mOrderIndicator.setViewPager(mVpOrder,item);

    }

    @OnClick({R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;
        }
    }
}
