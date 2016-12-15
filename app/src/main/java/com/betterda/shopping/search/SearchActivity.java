package com.betterda.shopping.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.search.contract.SearchContract;
import com.betterda.shopping.search.presenter.SearchPresenterImpl;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/15.
 */

public class SearchActivity extends BaseActivity<SearchContract.Presenter> implements SearchContract.View {
    @BindView(R.id.iv_search_delete)
    ImageView mIvDelete;
    @BindView(R.id.rv_search_jilu)
    RecyclerView mRvSearchJiLu;
    @BindView(R.id.rv_search_remen)
    RecyclerView mRvSearchRemen;
    @BindView(R.id.layout_loadingpager)
    LoadingPager mLoadingPager;
    @BindView(R.id.layout_recycleview)
    XRecyclerView mXRecyclerView;

    @Override
    protected SearchContract.Presenter onLoadPresenter() {
        return new SearchPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_search);
    }

    @Override
    public void init() {
        super.init();
        initRvRemen();
        initRvJilu();
        initxRV();
    }



    /**
     * 初始化热门
     */
    private void initRvRemen() {
        mRvSearchRemen.setLayoutManager(new GridLayoutManager(getmActivity(),5));
        mRvSearchRemen.setAdapter(getPresenter().getRvSearchRemenAdapter());
    }

    /**
     * 初始化记录
     */
    private void initRvJilu() {
        mRvSearchJiLu.setLayoutManager(new GridLayoutManager(getmActivity(),5));
        mRvSearchJiLu.setAdapter(getPresenter().getRvSearchJiluAdapter());

    }

    /**
     * 初始化内容的rv
     */
    private void initxRV() {

    }


}
