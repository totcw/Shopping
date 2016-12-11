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
import com.betterda.shopping.sort.model.Chose;
import com.betterda.shopping.sort.model.Sort;
import com.betterda.shopping.sort.model.Type;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Manifest;

/**
 * Created by Administrator on 2016/12/08
 */

public class SortPresenterImpl extends BasePresenter<SortContract.View, SortContract.Model> implements SortContract.Presenter {
    private CommonAdapter<Sort> mSortAdapter;
    private List<Sort> mSortList; //类别的容器
    private CommonAdapter<String> mNameAdapter;
    private List<String> mNameList, mNameLoadList; //商品的容器
    HeaderAndFooterRecyclerViewAdapter adapter;

    private CommonAdapter<Chose> mChoseAdapter;
    private List<Chose> mChoseList;//存放筛选的容器
    private HashMap<String, List<Type>> map; //存放筛选条件的map


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


    /**
     * 初始化筛选的适配器
     *
     * @return
     */
    @Override
    public RecyclerView.Adapter getRvSortChoseAdapter() {
        if (mChoseList == null) {
            mChoseList = new ArrayList<>();
            Chose chose = new Chose();
            chose.setType("品牌");
            chose.setName("全部");
            mChoseList.add(chose);
            Chose chose2 = new Chose();
            chose2.setType("价格");
            chose2.setName("全部");
            mChoseList.add(chose2);

        }
        mChoseAdapter = new CommonAdapter<Chose>(getView().getmActivity(), R.layout.item_rv_pp_shaixuan, mChoseList) {
            @Override
            public void convert(ViewHolder holder, final Chose s) {

                holder.setText(R.id.tv_pp_shaixuan, s.getType());
                holder.setText(R.id.tv_pp_shaixuan_name, s.getName());
                holder.setOnClickListener(R.id.linear_pp_shaixuan, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        getView().initRvSortType(s.getType());
                        getView().showType(true);
                    }
                });
            }
        };

        return mChoseAdapter;
    }


    /**
     * 初始化条件的适配器
     *
     * @return
     */
    @Override
    public RecyclerView.Adapter getRvSortTypeAdapter(final String type) {


        CommonAdapter<Type> adapter = new CommonAdapter<Type>(getView().getmActivity(), R.layout.item_rv_pp_shaixuan_type, getTypeList(type)) {
            @Override
            public void convert(final ViewHolder holder, final Type s) {

                holder.setText(R.id.tv_pp_shaixuan_type, s.getName());
                holder.setVisible(R.id.iv_pp_shaixuan_type_gou, s.isSelect());

                holder.setOnClickListener(R.id.linear_pp_shaixuan_type, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        refreshTypeState(holder, type);
                        refreshChoseRecycleview(type, s.getName());
                        getView().showType(false);
                    }
                });
            }
        };

        return adapter;
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

    /**
     * 刷新筛选的条件的选中状态
     * type 品牌
     *
     * @param holder
     */
    private void refreshTypeState(ViewHolder holder, String type) {

        if (map != null) {
            List<Type> list = map.get(type);
            if (list != null) {
                for (Type type1 : list) {
                    type1.setSelect(false);
                }
                if (holder != null) {
                    list.get(holder.getAdapterPosition()).setSelect(true);
                }
            }
        }

    }


    /**
     * 加载更多
     */
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

        RecyclerViewStateUtils.show(true, mNameLoadList, rv, getView().getmActivity(), 10);
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
     * 刷出筛选的rv的数据
     *
     * @param type 品牌
     * @param s
     */
    private void refreshChoseRecycleview(String type, String s) {
        for (Chose chose : mChoseList) {
            if (type.equals(chose.getType())) {
                chose.setName(s);
                break;
            }
        }
        mChoseAdapter.notifyDataSetChanged();
    }

    /**
     * 根据筛选的类型 来设置type的rv的数据
     *
     * @param type
     * @return
     */
    public List<Type> getTypeList(String type) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (map.get(type) == null) {
            List<Type> mTypeList = new ArrayList<>();
            if ("品牌".equals(type)) {
                Type type1 = new Type("全部", true);
                Type type2 = new Type("茅台");
                Type type3 = new Type("人头马");

                mTypeList.add(type1);
                mTypeList.add(type2);
                mTypeList.add(type3);

            } else if ("价格".equals(type)) {
                mTypeList.add(new Type("全部", true));
                mTypeList.add(new Type("0-499元"));
                mTypeList.add(new Type("500-999元"));
                mTypeList.add(new Type("1000-1499元"));
                mTypeList.add(new Type("1500-1999元"));
                mTypeList.add(new Type("2000元以上"));
            }
            map.put(type, mTypeList);
        }

        return map.get(type);

    }


    @Override
    public void clear() {
        if (map != null) {
            map.clear();
            map = null;
        }

        if (mChoseList != null) {
            mChoseList.clear();
            mChoseList = null;
        }

    }

    /**
     * 价格的确认按钮
     */
    @Override
    public void priceComfirm() {
        refreshTypeState(null, "价格");
        String stratPrice = getView().getStratPrice();
        String endPrice = getView().getEndPrice();
        refreshChoseRecycleview("价格", stratPrice+"-"+endPrice+"元");
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