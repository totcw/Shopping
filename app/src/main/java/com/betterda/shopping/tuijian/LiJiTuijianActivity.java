package com.betterda.shopping.tuijian;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.tuijian.contract.LiJiTuijianContract;
import com.betterda.shopping.tuijian.presenter.LiJiTuijianPresenterImpl;
import com.betterda.shopping.utils.ImageTools;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 立即推荐
 * Created by Administrator on 2016/12/20.
 */

public class LiJiTuijianActivity extends BaseActivity<LiJiTuijianContract.Presenter> implements LiJiTuijianContract.View {
    @BindView(R.id.topbar_myeweima)
    NormalTopBar mTopbarMyeweima;
    @BindView(R.id.iv_myerweima)
    ImageView mIvMyerweima;
    @BindView(R.id.loadpager_myeweima)
    LoadingPager mLoadpagerMyeweima;

    @Override
    protected LiJiTuijianContract.Presenter onLoadPresenter() {
        return new LiJiTuijianPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_lijituijian);
    }

    @Override
    public void init() {
        super.init();
        mTopbarMyeweima.setTitle("立即推荐");
        mTopbarMyeweima.setShareVisbility(true);
        mLoadpagerMyeweima.setonErrorClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onError();
            }
        });

    }
    @OnClick({R.id.bar_back, R.id.relative_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;
            case R.id.relative_share:
                share();
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //qq需要
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 分享
     */
    private void share() {
        View view = View.inflate(getmActivity(), R.layout.pp_productdetail_share, null);
        RecyclerView mRvShare = (RecyclerView) view.findViewById(R.id.rv_productdetail_share);
        Button mBtnCancel = (Button) view.findViewById(R.id.btn_pp_productdetail_cancel);
        mRvShare.setLayoutManager(new GridLayoutManager(getmActivity(),4));
        initRvShare(mRvShare);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePopupWindow();
            }
        });

        setUpPopupWindow(view);
    }

    private void initRvShare(RecyclerView mRvShare) {
        mRvShare.setAdapter(getPresenter().getRvShareAdapter());

    }

    @Override
    public void close() {
        closePopupWindow();
    }

    @Override
    public void setUrl(String url) {
        mIvMyerweima.setImageBitmap(ImageTools.generateQRCode(url,getmActivity()));
    }

    @Override
    public View getImageview() {
        return mIvMyerweima;
    }

    @Override
    public LoadingPager getLodapger() {
        return mLoadpagerMyeweima;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        UiUtils.backgroundAlpha(1.0f,getmActivity());
    }
}
