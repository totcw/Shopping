package com.betterda.shopping.comment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.comment.contract.AddCommentContract;
import com.betterda.shopping.comment.presenter.AddCommentPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加评论
 * Created by Administrator on 2017/1/5.
 */

public class AddCommentActivity extends BaseActivity<AddCommentContract.Presenter> implements AddCommentContract.View {
    @BindView(R.id.topbar_addcomment)
    NormalTopBar mTopbarAddcomment;
    @BindView(R.id.tv_addcomment)
    TextView mTvAddcomment;
    @BindView(R.id.rv_addcomment)
    RecyclerView mRvAddcomment;

    @Override
    protected AddCommentContract.Presenter onLoadPresenter() {
        return new AddCommentPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_addcomment);
    }

    @Override
    public void init() {
        super.init();
        mTopbarAddcomment.setTitle("添加评论");
    }

    public void initRv(RecyclerView.Adapter adapter) {
        mRvAddcomment.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvAddcomment.setAdapter(adapter);
    }



    @OnClick({R.id.bar_back, R.id.tv_addcomment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;
            case R.id.tv_addcomment:
                getPresenter().addComment();
                break;
        }
    }
}
