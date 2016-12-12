package com.betterda.shopping.productdetails;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.productdetails.contract.ProductDetailContract;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.GradationScrollView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情
 * Created by Administrator on 2016/12/12.
 */

public class ProductDetailActivity extends BaseActivity<ProductDetailContract.Presenter> implements ProductDetailContract.View, GradationScrollView.ScrollViewListener {


    @BindView(R.id.iv_productdetail_logo)
    ImageView mIvProductdetailLogo;
    @BindView(R.id.sv_productdetail)
    GradationScrollView mSvProductdetail;
    @BindView(R.id.tv_productdetail_title)
    TextView mTvProductdetailTitle;
    @BindView(R.id.relative_productdetail_title)
    RelativeLayout mRelativeProductdetailTitle;
    @BindView(R.id.ib_productdetail_back)
    ImageButton mIbProductdetailBack;
    @BindView(R.id.frame_productdetail_content)
    FrameLayout mFrameProductdetailContent;
    @BindView(R.id.linear_productdetail_content)
    LinearLayout mLinearProductdetailContent;

    private int height;
    private int height2;
    private int height3;
    private int mScreenHeight;
    private boolean isFirst;

    @Override
    protected ProductDetailContract.Presenter onLoadPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();

        setContentView(R.layout.activity_productdetail);

        mScreenHeight = UtilMethod.getHeight(getmActivity())-UtilMethod.statusHeight(getmActivity());


    }


    @Override
    public void initListener() {
        super.initListener();
        ViewTreeObserver vto = mIvProductdetailLogo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = mIvProductdetailLogo.getHeight();
                mSvProductdetail.setScrollViewListener(ProductDetailActivity.this);
            }
        });

        ViewTreeObserver vto2 = mRelativeProductdetailTitle.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height2 = mRelativeProductdetailTitle.getHeight();

            }
        });
        ViewTreeObserver vto3 = mLinearProductdetailContent.getViewTreeObserver();
        vto3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height3 = mLinearProductdetailContent.getHeight();
                if (height != 0&&height2!=0) {
                    if (!isFirst) {
                        int h = mScreenHeight - height3 + height-height2;
                        if (h > 0) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.height = h;
                            mFrameProductdetailContent.setLayoutParams(params);
                            isFirst = true;
                        }
                    }
                }

            }
        });
    }

    @OnClick(R.id.ib_productdetail_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_productdetail_back:
                back();
                break;
        }
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

        if (y <= 0) {   //设置标题的背景颜色
            mRelativeProductdetailTitle.setBackgroundColor(Color.argb( 0, 255, 255, 255));
            com.betterda.mylibrary.Utils.StatusBarCompat.setStatusBar5(getmActivity(),R.color.transparent);
        } else if (y > 0 && y <= height - height2) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变

            float scale = (float) y / (height - height2);
            float alpha = (255 * scale);
            mTvProductdetailTitle.setTextColor(Color.argb((int) alpha, 144, 151, 166));
            mRelativeProductdetailTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            if (y == height - height2) {
                com.betterda.mylibrary.Utils.StatusBarCompat.setStatusBar5(getmActivity(),R.color.white);
            }


        } else {    //滑动到banner下面设置普通颜色

            // relatview.setBackgroundColor(Color.argb( 255, 144,151,166));
            mRelativeProductdetailTitle.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }
}
