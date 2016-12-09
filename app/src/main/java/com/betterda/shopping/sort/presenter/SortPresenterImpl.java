package com.betterda.shopping.sort.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.betterda.mylibrary.recycleviehelper.HeaderAndFooterRecyclerViewAdapter;
import com.betterda.mylibrary.recycleviehelper.RecyclerViewStateUtils;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.sort.contract.SortContract;
import com.betterda.shopping.sort.model.Sort;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/08
 */

public class SortPresenterImpl extends BasePresenter<SortContract.View, SortContract.Model> implements SortContract.Presenter {
    private CommonAdapter<Sort> mSortAdapter;
    private List<Sort> mSortList; //类别的容器
    private CommonAdapter<String> mNameAdapter;
    private List<String> mNameList,mNameLoadList; //商品的容器
    HeaderAndFooterRecyclerViewAdapter adapter;


    @Override
    public void start() {
        initSortRecycleview();
        initNameRecycleview();
        getData();
    }


    /**
     * 初始化类别的recycleview
     */
    private void initSortRecycleview() {
        mSortList = new ArrayList<>();
        mSortAdapter = new CommonAdapter<Sort>(getView().getmActivity(), R.layout.item_rv_fragment_sort_sort, mSortList) {
            @Override
            public void convert(final ViewHolder holder, Sort s) {
                if (s != null) {
                    TextView view = holder.getView(R.id.tv_item_sort_sort);
                    view.setSelected(s.isSelect());
                    if (s.isSelect()) {
                        view.setTextColor(getView().getmActivity().getResources().getColor(R.color.white));
                    } else {
                        view.setTextColor(getView().getmActivity().getResources().getColor(R.color.activityMainPressed));
                    }
                    holder.setText(R.id.tv_item_sort_sort, s.getSortName());

                    holder.setOnClickListener(R.id.linear_item_sort_sort, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!getView().close())
                                refreshState(holder);
                        }
                    });
                }

            }
        };
        getView().initRvSortSort(mSortAdapter);
    }


    /**
     * 初始化商品的recycleview
     */
    public void initNameRecycleview() {
        mNameList = new ArrayList<>();
        mNameLoadList = new ArrayList<>();
        mNameAdapter = new CommonAdapter<String>(getView().getmActivity(), R.layout.item_rv_fragment_sort_name, mNameList) {
            @Override
            public void convert(ViewHolder holder, String s) {

            }
        };

        adapter = new HeaderAndFooterRecyclerViewAdapter(mNameAdapter);

        getView().initRvSortName(adapter);
    }


    private void getData() {
        for (int i = 0; i < 5; i++) {
            Sort sort = new Sort();
            sort.setSortName("白酒");
            mSortList.add(sort);
        }
        mSortAdapter.notifyDataSetChanged();

        for (int i = 0; i < 3; i++) {
            mNameLoadList.add(null);
            mNameList.add(null);
        }
        adapter.notifyDataSetChanged();


    }

    /**
     * 刷新类别的选中状态
     *
     * @param holder
     */
    private void refreshState(ViewHolder holder) {
        for (Sort sort : mSortList) {
            sort.setSelect(false);
        }
        mSortList.get(holder.getAdapterPosition()).setSelect(true);
        mSortAdapter.notifyDataSetChanged();
    }


    @Override
    public void loadShopping() {
        for (int i = 0; i < 3; i++) {
            mNameList.add(null);
            mNameLoadList.add(null);
        }
        adapter.notifyDataSetChanged();
        //设置加载状态
    }

    @Override
    public void showShopping(boolean isShow, RecyclerView rv) {

        RecyclerViewStateUtils.show(true, mNameLoadList, rv, getView().getmActivity(),10);
    }

    @Override
    public int getPosition(int position) {
        int headerViewsCount = adapter.getHeaderViewsCount();
        int itemCount = adapter.getInnerAdapter().getItemCount();
        if (position >= headerViewsCount + itemCount) {//如果是footer就占一列
            return 2;
        } else {
            return 1;
        }


    }

    /**
     * 初始化筛选的适配器
     * @return
     */
    @Override
    public RecyclerView.Adapter getRvSortChoseAdapter() {
        List<String> mChoseList = new ArrayList<>();
        mChoseList.add("品牌");
        mChoseList.add("价格");
        CommonAdapter<String> adapter = new CommonAdapter<String>(getView().getmActivity(),R.layout.item_rv_pp_shaixuan,mChoseList) {
            @Override
            public void convert(ViewHolder holder, final String s) {

                holder.setText(R.id.tv_pp_shaixuan, s);
                holder.setOnClickListener(R.id.linear_pp_shaixuan, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        getView().initRvSortType(s);
                        getView().showType(true);
                    }
                });
            }
        };

        return adapter;
    }


    /**
     * 初始化条件的适配器
     * @return
     */
    @Override
    public RecyclerView.Adapter getRvSortTypeAdapter(String type) {


        CommonAdapter<String> adapter = new CommonAdapter<String>(getView().getmActivity(),R.layout.item_rv_pp_shaixuan_type,getTypeList(type)) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setOnClickListener(R.id.linear_pp_shaixuan_type, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getView().showType(false);
                    }
                });
            }
        };

        return adapter;
    }

    public List<String> getTypeList(String type) {
        List<String> mChoseList = new ArrayList<>();
        for (int i=0;i<10;i++) {
            mChoseList.add(i + "");
        }
        return mChoseList;
    }



    @Override
    public void destroy() {
        if (mSortList != null) {
            mSortList.clear();
            mSortList = null;
        }

        if (mNameList != null) {
            mNameList.clear();
            mNameList = null;
        }
    }


}