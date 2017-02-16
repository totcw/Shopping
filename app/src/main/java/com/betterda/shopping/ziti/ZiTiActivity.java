package com.betterda.shopping.ziti;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.ziti.contract.ZiTiContract;
import com.betterda.shopping.ziti.presenter.ZiTiPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 自提列表
 * Created by Administrator on 2016/12/29.
 */

public class ZiTiActivity extends BaseActivity<ZiTiContract.Presenter> implements ZiTiContract.View {

    @BindView(R.id.layout_recycleview)
    XRecyclerView mRecycleview;
    @BindView(R.id.layout_loadingpager)
    LoadingPager mLoadingpager;


    @Override
    protected ZiTiContract.Presenter onLoadPresenter() {
        return new ZiTiPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_ziti);

    }

    @Override
    public void init() {
        super.init();

        initRv();
        mLoadingpager.setonErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onError();
            }
        });

    }


    @OnClick({R.id.iv_ziti_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_ziti_back:
                back();
                break;

        }
    }



    private void initRv() {
        mRecycleview.setVisibility(View.VISIBLE);
        mRecycleview.setPullRefreshEnabled(false);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRecycleview.setAdapter(getPresenter().getRvAdapter());

    }

    @Override
    public LoadingPager getLodapger() {
        return mLoadingpager;
    }
}
