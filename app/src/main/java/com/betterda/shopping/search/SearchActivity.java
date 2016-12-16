package com.betterda.shopping.search;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.search.contract.SearchContract;
import com.betterda.shopping.search.presenter.SearchPresenterImpl;
import com.betterda.shopping.widget.BusView;
import com.betterda.shopping.widget.SearchView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/15.
 */

public class SearchActivity extends BaseActivity<SearchContract.Presenter> implements SearchContract.View {
    @BindView(R.id.flowlayout_remen)
    TagFlowLayout mFlowLayoutRemen;
    @BindView(R.id.flowlayout_jilu)
    TagFlowLayout mFlowLayoutJilu;
    @BindView(R.id.layout_loadingpager)
    LoadingPager mLoadingPager;
    @BindView(R.id.layout_recycleview)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.linear_search_content)
    LinearLayout mLinearSearch;
    @BindView(R.id.searchview_search)
    SearchView mSearchView;
    @BindView(R.id.busview_search)
    BusView mBusView;

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
        mSearchView.setTvCancelVisable(true);
        initRemenFlow();
        initJiluFlow();
        initxRV();
    }

    @Override
    public void initListener() {
        super.initListener();
        mSearchView.setListener(new SearchView.onTextChangeListener() {
            @Override
            public void load(String content) {
                getPresenter().load(content);
            }

            @Override
            public void cancel() {
                mLoadingPager.hide();
                setRvSearchVisable(false);
            }
        });
    }

    @OnClick({R.id.relative_search_delete,R.id.tv__search_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv__search_cancel:
                back();
                break;
            case R.id.relative_search_delete:
                getPresenter().deleteJilu();
                break;
        }
    }


    private void initRemenFlow() {
        mFlowLayoutRemen.setAdapter(getPresenter().getFlowRemenAdapter());
        mFlowLayoutRemen.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                getPresenter().onTagClickListener(view, position, parent);
                return true;
            }
        });
    }

    private void initJiluFlow() {
        mFlowLayoutJilu.setAdapter(getPresenter().getFlowJiluAdapter());
        mFlowLayoutJilu.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                getPresenter().onJiluTagClickListener(view, position, parent);
                return true;
            }
        });
    }


    /**
     * 初始化内容的rv
     */
    private void initxRV() {
        mXRecyclerView.setLayoutManager(new GridLayoutManager(getmActivity(),2));
        mXRecyclerView.setPullRefreshEnabled(false);
        mXRecyclerView.setAdapter(getPresenter().getRvSearchAdapter());

    }

    public LoadingPager getLoadpager() {
        return mLoadingPager;
    }

    public BusView getmBusView() {
        return mBusView;
    }

    @Override
    public void setRvSearchVisable(boolean b) {
        mXRecyclerView.setVisibility(b?View.VISIBLE:View.INVISIBLE);
        mBusView.setVisibility(b?View.VISIBLE:View.INVISIBLE);
        mLinearSearch.setVisibility(b?View.INVISIBLE:View.VISIBLE);

    }

    @Override
    public void setEtSearch(String s) {
        mSearchView.setEtText(s);
    }


}
