package com.betterda.shopping.productdetails;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.view.RatioLayout;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.bus.BusActivity;
import com.betterda.shopping.productdetails.contract.ProductDetailContract;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.AddAndSub;
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
    @BindView(R.id.linear) //标题栏
            LinearLayout linear;
    @BindView(R.id.view) //标题栏 隐藏的view 5.0高度是0
            View viewL;
    @BindView(R.id.ratio_productdetail)
    RatioLayout mRatioLayout;
    @BindView(R.id.aas_productdetail)
    AddAndSub mAddAndSub;
    @BindView(R.id.loadpager_productdetail)
    LoadingPager mLoadingPager;
    @BindView(R.id.tv_productdetail_shopname)
    TextView mTvShopName;
    @BindView(R.id.tv_productdetail_spec)
    TextView mTvSpec;
    @BindView(R.id.tv_productdetail_price)
    TextView mTvPrice;
    @BindView(R.id.tv_productdetail_comment)
    TextView mTvComment;
    @BindView(R.id.tv_productdetail_commentrate)
    TextView mTvCommentRate;//好评率
    @BindView(R.id.tv_productdetail_add)
    TextView mTvAdd;
    @BindView(R.id.tv_productdetail_buy)
    TextView mTvBuy;
    @BindView(R.id.iv_productdetail_share)
    ImageView mIvShare;
    @BindView(R.id.linear_productdetail_bus)
    LinearLayout mLinearBus;
    @BindView(R.id.linear_productdetail_comment)
    LinearLayout mLinearComment;//评价


    private int height; //logo图片的高度
    private int height2; //标题栏的高度
    private int height3;//内容的实际高度
    private int mScreenHeight;//屏幕的高度
    private boolean isFirst; //用来判断只设置重新设置一次内容的高度

    @Override
    protected ProductDetailContract.Presenter onLoadPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_productdetail);
    }

    @Override
    public void init() {
        super.init();
        setStatusBar();


    }

    @Override
    public void initListener() {
        super.initListener();
        ViewTreeObserver vto = mRatioLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = mRatioLayout.getHeight();
                mSvProductdetail.setScrollViewListener(ProductDetailActivity.this);
            }
        });

        ViewTreeObserver vto2 = linear.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height2 = linear.getHeight();

            }
        });
        ViewTreeObserver vto3 = mLinearProductdetailContent.getViewTreeObserver();
        vto3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height3 = mLinearProductdetailContent.getHeight();
                if (height != 0 && height2 != 0) {
                    if (!isFirst) {
                        //保证只重新设置一次
                        int h = mScreenHeight - height3 + height - height2;
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

    @OnClick({R.id.ib_productdetail_back, R.id.linear_productdetail_comment, R.id.linear_productdetail_bus, R.id.tv_productdetail_buy,
            R.id.tv_productdetail_add, R.id.iv_productdetail_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_productdetail_back:
                back();
                break;
            case R.id.linear_productdetail_comment://评价

                break;
            case R.id.linear_productdetail_bus://购物车
                    UiUtils.startIntent(getmActivity(), BusActivity.class);
                break;
            case R.id.tv_productdetail_add://加入购物车

                break;
            case R.id.iv_productdetail_share://分享

                break;
            case R.id.tv_productdetail_buy://购买

                break;
        }
    }


    /**
     * 设置5.0以上的着色状态栏
     */
    private void setStatusBar() {
        //1. 首先将手机手机状态栏透明化：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //设置view的高度为状态栏的高度,这样标题栏才不会置顶
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = UtilMethod.statusHeight(getmActivity());
            viewL.setLayoutParams(layoutParams);
            //获取屏幕的高度
            mScreenHeight = UtilMethod.getHeight(getmActivity());
        } else {
            //5.0一下因为没有使用着色状态栏,要减去状态栏的高度
            mScreenHeight = UtilMethod.getHeight(getmActivity()) - UtilMethod.statusHeight(getmActivity());
        }
    }

    /**
     * scrollivew滑动的监听
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        //设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        }
        if (y <= 0) {
            //设置标题的背景颜色透明
            mRelativeProductdetailTitle.setBackgroundColor(Color.argb(0, 255, 255, 255));
            viewL.setBackgroundColor(Color.argb(0, 255, 255, 255));
        } else if (y > 0 && y <= height - height2) {
            //滑动距离小于logo图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / (height - height2);
            float alpha = (255 * scale);
            mTvProductdetailTitle.setTextColor(Color.argb((int) alpha, 144, 151, 166));
            mRelativeProductdetailTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            viewL.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            //当滑动高度等于logo图片的高度时,view的背景设置透明
            if (y == height - height2) {
                viewL.setBackgroundColor(Color.argb(0, 255, 255, 255));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.white));
                }
            }

        } else {
            //滑动到banner下面设置普通颜色
            mRelativeProductdetailTitle.setBackgroundColor(Color.argb(255, 255, 255, 255));
            viewL.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }
}
