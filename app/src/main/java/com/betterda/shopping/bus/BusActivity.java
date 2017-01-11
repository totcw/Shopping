package com.betterda.shopping.bus;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.bus.contract.BusContract;
import com.betterda.shopping.bus.presenter.BusPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/13.
 */

public class BusActivity extends BaseActivity<BusContract.Presenter> implements BusContract.View {
    @BindView(R.id.topbar_bus)
    NormalTopBar mTopBar;
    @BindView(R.id.iv_bus_all)
    ImageView mIvBusAll;//全选的图标
    @BindView(R.id.relative_bus_check)
    RelativeLayout mRelativeBusCheck; //全选
    @BindView(R.id.iv_bus_jiesuan)
    TextView mIvBusJiesuan;
    @BindView(R.id.iv_bus_delete)
    TextView mIvBusDelete;
    @BindView(R.id.tv_bus_money)
    TextView mTvBusMoney;
    @BindView(R.id.tv_bus_heji)
    TextView mTvHeji;
    @BindView(R.id.rv_bus)
    RecyclerView mRvBus;
    @BindView(R.id.loadpager_bus)
    LoadingPager loadpagerBus;

    @Override
    protected BusContract.Presenter onLoadPresenter() {
        return new BusPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_bus);
    }

    @Override
    public void init() {
        super.init();
        mTopBar.setTitle("购物车");
        mTopBar.setActionText("编辑");
        mTopBar.setActionTextVisibility(true);

    }

    @OnClick({R.id.relative_bus_check, R.id.iv_bus_jiesuan, R.id.iv_bus_delete, R.id.bar_back, R.id.bar_action})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_bus_check://全选
                getPresenter().checkAll();
                break;
            case R.id.iv_bus_jiesuan:
                if (mIvBusJiesuan.isSelected()) {
                    getPresenter().jiesuan();
                }
                break;
            case R.id.iv_bus_delete:
                if (mIvBusDelete.isSelected()) {
                    getPresenter().delete();
                }
                break;
            case R.id.bar_back:
                back();
                break;
            case R.id.bar_action://编辑
                getPresenter().edit();
                break;
        }
    }


    public void initRvBus(RecyclerView.Adapter adapter) {
        mRvBus.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvBus.setAdapter(adapter);
        mRvBus.addItemDecoration(new DividerItemDecoration(getmActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    /**
     * 改变结算和删除的状态
     *
     * @param change2
     */
    @Override
    public void setIvDeleteSelect(boolean change2) {
        mIvBusJiesuan.setSelected(change2);
        mIvBusDelete.setSelected(change2);
    }

    /**
     * 改变全选的状态
     *
     * @param change
     */
    @Override
    public void setIvAllSelect(boolean change) {
        mIvBusAll.setSelected(change);
    }

    @Override
    public void setActionText(String status) {
        mTopBar.setActionText(status);
    }

    /**
     * 更新合计
     *
     * @param money
     */
    @Override
    public void setHejiText(String money) {
        mTvBusMoney.setText(money);
    }

    @Override
    public void setStatusVisable(boolean isEdit) {
        mTvBusMoney.setVisibility(isEdit ? View.INVISIBLE : View.VISIBLE);
        mTvHeji.setVisibility(isEdit ? View.INVISIBLE : View.VISIBLE);
        mIvBusJiesuan.setVisibility(isEdit ? View.INVISIBLE : View.VISIBLE);
        mIvBusDelete.setVisibility(isEdit ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public TextView getTvHeji() {
        return mTvHeji;
    }

    @Override
    public LoadingPager getLodapger() {
        return loadpagerBus;
    }
}
