package com.betterda.shopping.sort.presenter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.betterda.mylibrary.recycleviehelper.HeaderAndFooterRecyclerViewAdapter;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.productdetails.ProductDetailActivity;
import com.betterda.shopping.sort.contract.SortContract;
import com.betterda.shopping.sort.model.Chose;
import com.betterda.shopping.sort.model.Shopping;
import com.betterda.shopping.sort.model.Sort;
import com.betterda.shopping.sort.model.SortModelImpl;
import com.betterda.shopping.sort.model.Type;
import com.betterda.shopping.utils.UiUtils;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/08
 */

public class SortPresenterImpl extends BasePresenter<SortContract.View, SortContract.Model> implements SortContract.Presenter {
    private CommonAdapter<Sort> mSortAdapter;
    private CommonAdapter<Shopping> mNameAdapter;
    private CommonAdapter<Chose> mChoseAdapter;
    private CommonAdapter<Sort> mSortPAdapter;


    private List<Sort> mSortList; //类别的容器
    private List<Sort> mSortPList; //排序的容器
    private List<Shopping> mNameList; //商品的容器
    private List<Chose> mChoseList;//存放筛选的容器
    private HashMap<String, List<Type>> map; //存放筛选条件的map


    @Override
    public void start() {
        attachModel(new SortModelImpl());
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
        mNameAdapter = new CommonAdapter<Shopping>(getView().getmActivity(), R.layout.item_rv_fragment_sort_name, mNameList) {
            @Override
            public void convert(ViewHolder holder, Shopping s) {

                if (null != s) {

                    holder.setText(R.id.tv_item_sort_name, s.getShopName());
                    holder.setText(R.id.tv_item_sort_account, s.getSpec());
                    holder.setText(R.id.tv_item_sort_money, "￥"+s.getPrice());

                    holder.setOnClickListener(R.id.sv_item_duobao, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            UiUtils.startIntent(getView().getmActivity(), ProductDetailActivity.class);
                        }
                    });

                    holder.setOnClickListener(R.id.iv_item_sort_name_add, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getView().addBus(1);
                        }
                    });
                }


            }
        };


        getView().initRvSortName(mNameAdapter);
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
                setTextColor(s.isSelect(), holder, R.id.tv_pp_shaixuan_type, R.color.activityMainPressed, R.color.shouye_fenlei_red);
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

    /**
     * 初始化排序的适配器
     *
     * @return
     */
    @Override
    public RecyclerView.Adapter getRvSortNameSortAdapter() {
        if (mSortPList == null) {
            mSortPList = new ArrayList<>();
            getModel().addSort(mSortPList);
        }
        mSortPAdapter = new CommonAdapter<Sort>(getView().getmActivity(), R.layout.item_rv_pp_sort, mSortPList) {
            @Override
            public void convert(final ViewHolder holder, Sort s) {
                holder.setText(R.id.tv_item_sort_sort_p, s.getSortName());
                setTextColor(s.isSelect(), holder, R.id.tv_item_sort_sort_p, R.color.activityMainPressed, R.color.sortBlue);
                holder.setOnClickListener(R.id.linear_pp_sort, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getModel().clear(mSortPList, holder.getAdapterPosition());
                        getView().close();
                        // mSortPAdapter.notifyDataSetChanged();
                    }
                });
            }
        };

        return mSortPAdapter;
    }


    private void getData() {
        for (int i = 0; i < 5; i++) {
            Sort sort = new Sort();
            sort.setSortName("白酒");
            mSortList.add(sort);
        }
        mSortAdapter.notifyDataSetChanged();

        for (int i = 0; i < 13; i++) {
            mNameList.add(new Shopping());
        }
        mNameAdapter.notifyDataSetChanged();


    }

    /**
     * 设置文字的颜色
     *
     * @param select
     * @param holder
     * @param activityMainPressed
     * @param sortBlue
     */
    private void setTextColor(boolean select, ViewHolder holder, int id, int activityMainPressed, int sortBlue) {
        if (select) {
            holder.setTextColor(id, getView().getmActivity().getResources().getColor(sortBlue));
        } else {
            holder.setTextColor(id, getView().getmActivity().getResources().getColor(activityMainPressed));
        }
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
     * 刷新筛选的rv的数据
     *
     * @param type 品牌 传入null 表示要清空
     * @param s
     */
    private void refreshChoseRecycleview(String type, String s) {
        for (Chose chose : mChoseList) {
            if (type != null) {
                if (type.equals(chose.getType())) {
                    chose.setName(s);
                    break;
                }
            } else {
                chose.setName(s);
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


    /**
     * 价格的确认按钮
     */
    @Override
    public void priceComfirm() {
        String stratPrice = getView().getStratPrice();
        String endPrice = getView().getEndPrice();
        if (!TextUtils.isEmpty(stratPrice) && !TextUtils.isEmpty(endPrice)) {
            refreshTypeState(null, "价格");
            refreshChoseRecycleview("价格", stratPrice + "-" + endPrice + "元");
        }
    }

    /**
     * 清空筛选
     */
    @Override
    public void clearChose() {
        refreshChoseRecycleview(null, "全部");

    }

    /**
     * 确认筛选
     */
    @Override
    public void comfirmChose() {

    }

    /**
     * 清空筛选条件数据
     */
    @Override
    public void clear() {
        if (map != null) {
            map.clear();
            map = null;
        }

     /*   if (mChoseList != null) {
            mChoseList.clear();
            mChoseList = null;
        }*/

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