package com.betterda.shopping.sort;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.Utils.StatusBarCompat;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.search.SearchActivity;
import com.betterda.shopping.sort.contract.SortContract;
import com.betterda.shopping.sort.presenter.SortPresenterImpl;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.widget.TwoToolBarView;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 分类
 * Created by Administrator on 2016/12/8.
 */

public class SortFragment extends BaseFragment<SortContract.Presenter> implements SortContract.View {

    @BindView(R.id.relative_search)
    RelativeLayout mRelativeSearch;
    @BindView(R.id.linear_search)
    LinearLayout mLinearSearch;
    @BindView(R.id.linear_sort)
    FrameLayout mLinearSort;//根布局
    @BindView(R.id.iv_layout_search_delete)
    ImageView mIvSearchDelete;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv__search_cancel)
    TextView mTvSearchCancel;
    @BindView(R.id.rv_fragment_sort_sort)
    RecyclerView mRvSortSort;
    @BindView(R.id.rv_fragment_sort_name)
    XRecyclerView mRvSortName;
    @BindView(R.id.ttbv_fragmen_sort)
    TwoToolBarView mTtbvSort;
    @BindView(R.id.loadpager_fragment_sort_name)
    LoadingPager mLoadPagerSortName;
    @BindView(R.id.loadpager_fragment_sort)
    LoadingPager mLoadPagerSort;


    /**
     * popuwindow筛选条件界面的相关view
     */
    private LinearLayout mLinearSortNameChose; //筛选界面
    private LinearLayout mLinearSortNameChoseType;
    private LinearLayout mLinearSortNameChosePrice;
    private RecyclerView mRvSortType; //筛选条件的rv
    private EditText mEtNameStart;
    private EditText mEtNameEnd;
    private TextView mTvSortName;


    private int pressNum = -1;//用来判断点击的是排序还是筛选

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_sort, null);
    }

    @Override
    protected SortContract.Presenter onLoadPresenter() {
        return new SortPresenterImpl();
    }





    @Override
    public void initData() {
        super.initData();
        //禁止输入法弹出
        mEtSearch.setFocusable(false);

        mTtbvSort.setFirstTitle("综合排序");
        mTtbvSort.setSecondTitle("筛选");


    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//隐藏
        } else {
            StatusBarCompat.setStatusBar5(getmActivity(),R.color.white);
            ((MainActivity)getmActivity()).getmBvMain().setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.et_search, R.id.mfiv_twotool_fist, R.id.mfiv_twotool_second, R.id.linear_fragment_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search://搜索
                if (!close()) {
                   UiUtils.startIntent(getmActivity(), SearchActivity.class);
                }
                break;
            case R.id.mfiv_twotool_fist: //全部排序
                choseSort(0);
                break;
            case R.id.mfiv_twotool_second://筛选
                choseSort(1);
                break;
            case R.id.linear_fragment_sort://类别的rv
                close();
                break;


        }
    }


    /**
     * 初始化类别rv
     *
     * @param adapter
     */
    public void initRvSortSort(RecyclerView.Adapter adapter) {
        mRvSortSort.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvSortSort.setAdapter(adapter);
    }

    /**
     * 初始化商品的rv
     *
     * @param adapter
     */
    public void initRvSortName(RecyclerView.Adapter adapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getmActivity(), 2);
        mRvSortName.setLayoutManager(gridLayoutManager);
        mRvSortName.setLoadingMoreEnabled(true);
        mRvSortName.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRvSortName.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });
        mRvSortName.setAdapter(adapter);
    }

    /**
     * 选择排序或者筛选
     *
     * @param num
     */
    private void choseSort(int num) {
        if (pressNum == num) {
            if (close()) {
                return;
            }
        } else {
            close();
        }
        pressNum = num;
        int idvHeight = ((MainActivity) getmActivity()).getmIdvShouye().getHeight();
        int screenHeight = UtilMethod.getHeight(getmActivity());
        int height = screenHeight - idvHeight - mLinearSearch.getHeight() - mTtbvSort.getHeight() - UtilMethod.statusHeight(getmActivity());
        switch (num) {
            case 0:
                mTtbvSort.setFirstSelect(!mTtbvSort.isFirstSelected());
                View view1 = getPpView(R.layout.pp_frament_sort_sort);
                initRvSortNameSort(view1);
                setUpPopupWindow(view1, mTtbvSort, mTtbvSort.getWidth(), height);
                break;
            case 1:
                mTtbvSort.setSecondSelect(!mTtbvSort.isSecondSelected());
                View view2 = getPpView(R.layout.pp_fragment_sort_name);
                initChose(view2);
                initChoseType(view2);
                setUpPopupWindow(view2, mTtbvSort, mTtbvSort.getWidth(), height);
                break;
        }


    }

    /**
     * 初始化排序的rv
     *
     * @param view1
     */
    private void initRvSortNameSort(View view1) {
        RecyclerView rvSortNameSort = (RecyclerView) view1.findViewById(R.id.rv_pp_sort_name_sort);
        rvSortNameSort.setLayoutManager(new GridLayoutManager(getmActivity(), 3));
        rvSortNameSort.setAdapter(getPresenter().getRvSortNameSortAdapter());
    }

    /**
     * 初始化筛选的界面
     *
     * @param view2
     */
    private void initChose(View view2) {
        RecyclerView rvSortChose = (RecyclerView) view2.findViewById(R.id.rv_pp_sort_name_chose);
        initRvSortChose(rvSortChose);
        TextView mTvSortNameClear = (TextView) view2.findViewById(R.id.tv_sort_name_clear);
        TextView mTvSortNameComfirm = (TextView) view2.findViewById(R.id.tv_sort_name_comfirm);
        mTvSortNameClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().clearChose();
            }
        });
        mTvSortNameComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().comfirmChose();
            }
        });

    }

    /**
     * 初始化筛选的条件界面
     *
     * @param view2
     */
    private void initChoseType(View view2) {
        mEtNameStart = (EditText) view2.findViewById(R.id.et_pp_sort_name_start);
        mEtNameEnd = (EditText) view2.findViewById(R.id.et_pp_sort_name_end);
        mTvSortName = (TextView) view2.findViewById(R.id.tv_pp_sort_name);
        mLinearSortNameChose = (LinearLayout) view2.findViewById(R.id.linear_sort_name_shaixuan);
        mLinearSortNameChoseType = (LinearLayout) view2.findViewById(R.id.linear_sort_name_shaixuan_type);
        mLinearSortNameChosePrice = (LinearLayout) view2.findViewById(R.id.linear_pp_sort_name_price);
        mRvSortType = (RecyclerView) view2.findViewById(R.id.rv_pp_sort_name_type);
        mRvSortType.addItemDecoration(new DividerItemDecoration(getmActivity(), DividerItemDecoration.VERTICAL_LIST));

        mLinearSortNameChosePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mTvSortName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showType(false);
                getPresenter().priceComfirm();
            }
        });
    }

    /**
     * 初始化筛选的rv
     *
     * @param rvSortChose
     */
    private void initRvSortChose(RecyclerView rvSortChose) {
        rvSortChose.setLayoutManager(new LinearLayoutManager(getmActivity()));
        rvSortChose.setAdapter(getPresenter().getRvSortChoseAdapter());
        rvSortChose.addItemDecoration(new DividerItemDecoration(getmActivity(), DividerItemDecoration.VERTICAL_LIST));

    }

    /**
     * 初始化筛选界面的条件rv
     *
     * @param type
     */
    public void initRvSortType(String type,String name) {
        mRvSortType.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvSortType.setAdapter(getPresenter().getRvSortTypeAdapter(type,name));
        if ("价格".equals(type)) {
            mLinearSortNameChosePrice.setVisibility(View.VISIBLE);
        } else {
            mLinearSortNameChosePrice.setVisibility(View.GONE);
        }

    }



/*    *
     * 增加购物车的数量
     * @param amount
     */
    public void addBus(int amount) {
        String trim = ((MainActivity)getmActivity()).getmBvMain().getBusText();
        try {
            int count = Integer.parseInt(trim) + amount;
            ((MainActivity)getmActivity()).getmBvMain().setTvBusText(count+"");
            ((MainActivity)getmActivity()).getmBvMain().setTvBusVisable(true);
        } catch (Exception e) {

        }
    }

    @Override
    public ViewGroup getViewgroup() {
        return mLinearSort;
    }

    @Override
    public View getBusView() {
      return   ((MainActivity) getActivity()).getmBvMain();
    }

    /*    *
         * 获取购物车的数量
         * @return
         */
    public String getBusText() {
       return ((MainActivity)getmActivity()).getmBvMain().getBusText();

    }


    /**
     * 获取popupwindow的view
     *
     * @return
     */
    @NonNull
    private View getPpView(int layoutId) {
        View view1 = View.inflate(getmActivity(), layoutId, null);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePopupWindow();
            }
        });
        return view1;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        mTtbvSort.setSecondSelect(false);
        mTtbvSort.setFirstSelect(false);
        getPresenter().reFreshChose();

        //清楚popupwindow中的容器
       // getPresenter().clear();

    }

    /**
     * 用来判断popupwindow是否关闭
     *
     * @return
     */
    public boolean close() {
        if (getPopupWindow() != null && getPopupWindow().isShowing()) {
            closePopupWindow();
            return true;
        }
        return false;
    }

    /**
     * 显示筛选popupwindow的条件界面
     */
    @Override
    public void showType(boolean isShow) {
        if (mLinearSortNameChose != null) {
            mLinearSortNameChose.setVisibility(isShow ? View.INVISIBLE : View.VISIBLE);
        }
        if (mLinearSortNameChoseType != null) {
            mLinearSortNameChoseType.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        }

    }

    @Override
    public String getStratPrice() {
        return mEtNameStart.getText().toString().trim();
    }

    @Override
    public String getEndPrice() {
        return mEtNameEnd.getText().toString().trim();
    }


}
