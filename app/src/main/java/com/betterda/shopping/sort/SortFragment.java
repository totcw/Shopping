package com.betterda.shopping.sort;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.mylibrary.recycleviehelper.EndlessRecyclerOnScrollListener;
import com.betterda.mylibrary.recycleviehelper.RecyclerViewStateUtils;
import com.betterda.mylibrary.view.LoadingFooter;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.home.MainActivity;
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
    @BindView(R.id.iv_layout_search_delete)
    ImageView mIvSearchDelete;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv__search_cancel)
    TextView mTvSearchCancel;
    @BindView(R.id.rv_fragment_sort_sort)
    RecyclerView mRvSortSort;
    @BindView(R.id.rv_fragment_sort_name)
    RecyclerView mRvSortName;
    @BindView(R.id.ttbv_fragmen_sort)
    TwoToolBarView mTtbvSort;
    @BindView(R.id.loadpager_fragment_sort_name)
    LoadingPager mLoadPagerSortName;
    @BindView(R.id.loadpager_fragment_sort)
    LoadingPager mLoadPagerSort;

    private LinearLayout mLinearSortNameChose; //筛选界面
    private LinearLayout mLinearSortNameChoseType;
    private RecyclerView mRvSortType; //筛选条件的rv

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

        getPresenter().start();
    }



    @OnClick({R.id.et_search, R.id.mfiv_twotool_fist, R.id.mfiv_twotool_second,R.id.linear_fragment_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search://搜索
                if (!close()) {
                    UiUtils.showToast(getmActivity(),"搜索");
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
     * @param adapter
     */
    public void initRvSortSort(RecyclerView.Adapter adapter) {
        mRvSortSort.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvSortSort.setAdapter(adapter);
    }
    /**
     * 初始化商品的rv
     * @param adapter
     */
    public void initRvSortName(RecyclerView.Adapter adapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getmActivity(), 2);
        mRvSortName.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //动态的改变当前positon 占几列
                return getPresenter().getPosition(position);
            }
        });
        mRvSortName.addOnScrollListener(new EndlessRecyclerOnScrollListener(getmActivity()){
            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                if (LoadingFooter.State.Normal == RecyclerViewStateUtils.getFooterViewState(mRvSortName)) {
                    //设置为加载状态
                    RecyclerViewStateUtils.setFooterViewState(getmActivity(), mRvSortName, LoadingFooter.State.Loading, null);
                    //网络请求
                    getPresenter().loadShopping();
                }
            }
            @Override
            public void show(boolean isShow) {
                super.show(isShow);

                getPresenter().showShopping(isShow,mRvSortName);

            }
        });
        mRvSortName.setAdapter(adapter);
    }

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
        int height = screenHeight - idvHeight - mLinearSearch.getHeight() - mTtbvSort.getHeight()-UtilMethod.statusHeight(getmActivity());
        switch (num) {
            case 0:
                mTtbvSort.setFirstSelect(!mTtbvSort.isFirstSelected());
                View view1 = getPpView(R.layout.pp_frament_sort_sort);
                setUpPopupWindow(view1,mTtbvSort,mTtbvSort.getWidth(),height);
                break;
            case 1:
                mTtbvSort.setSecondSelect(!mTtbvSort.isSecondSelected());
                View view2 = getPpView(R.layout.pp_fragment_sort_name);
                mLinearSortNameChose = (LinearLayout) view2.findViewById(R.id.linear_sort_name_shaixuan);
                mLinearSortNameChoseType = (LinearLayout) view2.findViewById(R.id.linear_sort_name_shaixuan_type);
                RecyclerView rvSortChose = (RecyclerView) view2.findViewById(R.id.rv_pp_sort_name_chose);
                mRvSortType = (RecyclerView) view2.findViewById(R.id.rv_pp_sort_name_type);
                mRvSortType.addItemDecoration(new DividerItemDecoration(getmActivity(),DividerItemDecoration.VERTICAL_LIST));
                initRvSortChose(rvSortChose);

                setUpPopupWindow(view2,mTtbvSort,mTtbvSort.getWidth(),height);
                break;
        }



    }

    /**
     * 初始化筛选的rv
     * @param rvSortChose
     */
    private void initRvSortChose(RecyclerView rvSortChose) {
        rvSortChose.setLayoutManager(new LinearLayoutManager(getmActivity()));
        rvSortChose.setAdapter(getPresenter().getRvSortChoseAdapter());
        rvSortChose.addItemDecoration(new DividerItemDecoration(getmActivity(),DividerItemDecoration.VERTICAL_LIST));

    }

    /**
     * 初始化筛选界面的条件rv
     * @param type
     */
    public void initRvSortType(String type) {
        mRvSortType.setLayoutManager(new LinearLayoutManager(getmActivity()));
        mRvSortType.setAdapter(getPresenter().getRvSortTypeAdapter(type));

    }


    /**
     * 获取popupwindow的view
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

    @Override
    public void showType(boolean isShow) {
        if (mLinearSortNameChose != null) {
            mLinearSortNameChose.setVisibility(isShow?View.INVISIBLE:View.VISIBLE);
        }
        if (mLinearSortNameChoseType != null) {
            mLinearSortNameChoseType.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        }

    }


}
