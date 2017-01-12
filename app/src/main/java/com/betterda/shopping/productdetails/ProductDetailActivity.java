package com.betterda.shopping.productdetails;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
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
import com.betterda.shopping.comment.CommentActivity;
import com.betterda.shopping.factory.LoadImageFactory;
import com.betterda.shopping.javabean.ShopDetail;
import com.betterda.shopping.order.OrderComfirmActivity;
import com.betterda.shopping.productdetails.contract.ProductDetailContract;
import com.betterda.shopping.productdetails.model.Share;
import com.betterda.shopping.productdetails.presenter.ProductDetailPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.AddAndSub;
import com.betterda.shopping.widget.GradationScrollView;
import com.umeng.socialize.UMShareAPI;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.tv_productdetail_pricemember)
    TextView mTvPriceMember;
    @BindView(R.id.tv_productdetail_comment)
    TextView mTvComment;
    @BindView(R.id.tv_productdetail_commentrate)
    TextView mTvCommentRate;//好评率
    @BindView(R.id.tv_productdetail_add)
    TextView mTvAdd;
    @BindView(R.id.tv_productdetail_bus)
    TextView mTvBus;//购物车的数字
    @BindView(R.id.tv_productdetail_buy)
    TextView mTvBuy;
    @BindView(R.id.relative_productdetail_share)
    RelativeLayout mRelativeShare;
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
        return new ProductDetailPresenterImpl();
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
            R.id.tv_productdetail_add, R.id.relative_productdetail_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_productdetail_back:
                back();
                break;
            case R.id.linear_productdetail_comment://评价
                UiUtils.startIntent(getmActivity(), CommentActivity.class);
                break;
            case R.id.linear_productdetail_bus://购物车
                UiUtils.startIntent(getmActivity(), BusActivity.class);
                break;
            case R.id.tv_productdetail_add://加入购物车
                getPresenter().addBus();
                break;
            case R.id.relative_productdetail_share://分享
                share();
                break;
            case R.id.tv_productdetail_buy://购买
                addBuy();
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
     * 设置5.0以上的着色状态栏
     */
    private void setStatusBar() {
        //1. 首先将手机手机状态栏透明化：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //设置view的高度为状态栏的高度,这样标题栏才不会置顶
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = UtilMethod.statusHeight(getmActivity());
            viewL.setLayoutParams(layoutParams);
            //获取屏幕的高度
            mScreenHeight = UtilMethod.getHeight(getmActivity());
        } else {
            //6.0一下因为没有使用着色状态栏,要减去状态栏的高度
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.white));
                }
            }

        } else {
            //滑动到banner下面设置普通颜色
            mRelativeProductdetailTitle.setBackgroundColor(Color.argb(255, 255, 255, 255));
            viewL.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }


    /**
     * 立即购买
     */
    private void addBuy() {
        View view = View.inflate(getmActivity(), R.layout.pp_productdetail_buy, null);
        ImageView mIvPpLogo = (ImageView) view.findViewById(R.id.iv_pp_productdetail_logo);
        TextView mTvPpPrice = (TextView) view.findViewById(R.id.tv_pp_productdetail_price);
        TextView mTvPpPriceMember = (TextView) view.findViewById(R.id.tv_pp_productdetail_pricemember);
        AddAndSub mAasub = (AddAndSub) view.findViewById(R.id.aas_pp_productdetail_buy);
        ImageButton mIvPpdelete = (ImageButton) view.findViewById(R.id.iv_pp_productdetail_delete);
        Button mBtnComfirm = (Button) view.findViewById(R.id.btn_pp_productdetail_buy);

        mAasub.setAmount(mAddAndSub.getAmount());

        mIvPpdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePopupWindow();
            }
        });

        mBtnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePopupWindow();
                UiUtils.startIntent(getmActivity(), OrderComfirmActivity.class);
            }
        });
        setUpPopupWindow(view);
    }

    /**
     * 分享
     */
    private void share() {
        View view = View.inflate(getmActivity(), R.layout.pp_productdetail_share, null);
        RecyclerView  mRvShare = (RecyclerView) view.findViewById(R.id.rv_productdetail_share);
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
    public TextView getTvBus() {
        return mTvBus;
    }

    @Override
    public AddAndSub getMaddAndSub() {
        return mAddAndSub;
    }

    @Override
    public void setValue(ShopDetail data) {
        mTvShopName.setText(data.getProductName());
        mTvComment.setText("("+data.getQuantity()+"人评价)");
        mTvCommentRate.setText(data.getDegree());
        mTvSpec.setText(data.getSpec());
        mTvPrice.setText("￥:"+data.getSalePrice());
        mTvPriceMember.setText("会员价￥:"+data.getSalePrice());
        if (data.getBigPicture() != null) {
            LoadImageFactory.getLoadImageInterface().loadImageFit(getmActivity(),data.getBigPicture(),mIvProductdetailLogo);
        }
    }

    @Override
    public LoadingPager getLodapger() {
        return mLoadingPager;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        UiUtils.backgroundAlpha(1.0f,getmActivity());
    }


}
