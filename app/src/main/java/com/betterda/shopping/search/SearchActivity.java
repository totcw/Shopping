package com.betterda.shopping.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.search.contract.SearchContract;
import com.betterda.shopping.search.presenter.SearchPresenterImpl;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.BusView;
import com.betterda.shopping.widget.SearchView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索
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
    @BindView(R.id.frame_search)
    FrameLayout mFrameSearch;
    @BindView(R.id.relative_search_delete)
    RelativeLayout mRelativeDelete;
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
        mLoadingPager.setonErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onError();
            }
        });
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
                getPresenter().reFresh();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UtilMethod.isLogin(getmActivity())) {
            mRelativeDelete.setVisibility(View.VISIBLE);
        } else {
            mRelativeDelete.setVisibility(View.GONE);
        }
        getPresenter().getDataForBusCount();
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


    public void initRemenFlow(TagAdapter adapter) {
        mFlowLayoutRemen.setAdapter(adapter);
        mFlowLayoutRemen.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                getPresenter().onTagClickListener(view, position, parent);
                return true;
            }
        });
    }

    public void initJiluFlow(TagAdapter adapter) {
        mFlowLayoutJilu.setAdapter(adapter);
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
    public void initxRV(RecyclerView.Adapter adapter) {
        mXRecyclerView.setLayoutManager(new GridLayoutManager(getmActivity(),2));
        mXRecyclerView.setPullRefreshEnabled(false);
        mXRecyclerView.setLoadingMoreEnabled(false);
        mXRecyclerView.setAdapter(adapter);

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

    @Override
    public ViewGroup getViewGroup() {
        return mFrameSearch;
    }

    @Override
    public LoadingPager getLodapger() {
        return mLoadingPager;
    }
}
