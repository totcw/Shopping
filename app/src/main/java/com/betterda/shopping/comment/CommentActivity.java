package com.betterda.shopping.comment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.bus.BusActivity;
import com.betterda.shopping.comment.contract.CommentContract;
import com.betterda.shopping.comment.presenter.CommentPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户评论
 * Created by Administrator on 2016/12/15.
 */

public class CommentActivity extends BaseActivity<CommentContract.Presenter> implements CommentContract.View {
    @BindView(R.id.topbar_comment)
    NormalTopBar mTopbarComment;
    @BindView(R.id.rv_comment)
    XRecyclerView mRvComment;
    @BindView(R.id.loadpager_comment)
    LoadingPager mLoadpagerComment;

    @Override
    protected CommentContract.Presenter onLoadPresenter() {
        return new CommentPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_comment);
    }

    @Override
    public void init() {
        super.init();
        mTopbarComment.setTitle("商品评论");
        initRvComment();
    }


    @OnClick({R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;

        }
    }


    private void initRvComment() {
        mRvComment.setLayoutManager(new LinearLayoutManager(getmActivity()));
       // mRvComment.addItemDecoration(new DividerItemDecoration(getmActivity(),DividerItemDecoration.VERTICAL_LIST));
        mRvComment.setAdapter(getPresenter().getRvCommentAdapter());
        mRvComment.setLoadingMoreEnabled(true);
        mRvComment.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });

    }
}
