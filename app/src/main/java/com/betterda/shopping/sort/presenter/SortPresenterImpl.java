package com.betterda.shopping.sort.presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterda.mylibrary.recycleviehelper.HeaderAndFooterRecyclerViewAdapter;
import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.factory.LoadImageFactory;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.ShopBrand;
import com.betterda.shopping.login.LoginActivity;
import com.betterda.shopping.productdetails.ProductDetailActivity;
import com.betterda.shopping.sort.contract.SortContract;
import com.betterda.shopping.sort.model.Chose;
import com.betterda.shopping.sort.model.Shopping;
import com.betterda.shopping.sort.model.Sort;
import com.betterda.shopping.sort.model.SortModelImpl;
import com.betterda.shopping.sort.model.Type;
import com.betterda.shopping.utils.AnimUtils;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.GsonParse;
import com.betterda.shopping.utils.GsonTools;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
import com.betterda.shopping.welcome.WelcomeActivity;
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

    private String productType;//商品类型
    private String sort; //排序
    private String brand, lastBrand; //品牌 ,记录上一选择的品牌
    private String beginPrice, lastBeginPrice; //起始价格
    private String endPrice, lastEndPrice; //始末价格
    private int pangeNo = 1;//分页加载的页数


    @Override
    public void start() {
        attachModel(new SortModelImpl());
        initSortRecycleview();
        initNameRecycleview();
        getView().getLodapger().setLoadVisable();
    }


    /**
     * 初始化商品类别的recycleview
     */
    private void initSortRecycleview() {
        mSortList = new ArrayList<>();
        mSortAdapter = new CommonAdapter<Sort>(getView().getmActivity(), R.layout.item_rv_fragment_sort_sort, mSortList) {
            @Override
            public void convert(final ViewHolder holder, final Sort s) {
                if (s != null) {
                    TextView view = holder.getView(R.id.tv_item_sort_sort);
                    view.setSelected(s.isSelect());
                    if (s.isSelect()) {
                        //记录商品类型
                        productType = s.getCatalogname();
                        view.setTextColor(getView().getmActivity().getResources().getColor(R.color.white));
                    } else {
                        view.setTextColor(getView().getmActivity().getResources().getColor(R.color.activityMainPressed));
                    }
                    holder.setText(R.id.tv_item_sort_sort, s.getCatalogname());

                    holder.setOnClickListener(R.id.linear_item_sort_sort, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!getView().close()) {
                                //记录商品类型
                                productType = s.getCatalogname();
                                refreshState(holder);
                                pangeNo = 1;
                                getShopList();
                            }
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
            public void convert(ViewHolder holder, final Shopping s) {

                if (null != s) {

                    holder.setText(R.id.tv_item_sort_name, s.getProductName());
                    holder.setText(R.id.tv_item_sort_money, "￥" + s.getSalePrice());
                    holder.setText(R.id.tv_item_sort_moneymember, "会员价:￥" + s.getVipPrice());
                    ImageView imageView = holder.getView(R.id.sv_item_duobao);
                    LoadImageFactory.getLoadImageInterface().loadImageFit(getView().getmActivity(), s.getLittlePicture(), imageView);
                    View view = holder.getView(R.id.iv_item_sort_name_add);
                    holder.setOnClickListener(R.id.linear_item_sort_name, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getView().getmActivity(), ProductDetailActivity.class);
                            intent.putExtra("productId", s.getId());
                            UiUtils.startIntent(getView().getmActivity(), intent);
                        }
                    });

                    holder.setOnClickListener(R.id.iv_item_sort_name_add, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (UtilMethod.isLogin(getView().getmActivity())) {
                                ViewGroup viewgroup = getView().getViewgroup();
                                View busView = getView().getBusView();
                                AnimUtils.playAnimation(getView().getmActivity(), view, viewgroup, busView, new AnimUtils.AnimEndListener() {
                                    @Override
                                    public void end() {

                                        getView().getRxManager().add(NetWork.getNetService()
                                                .addBus(getView().getAccount(), getView().getToken(), s.getId(), "1")
                                                .compose(NetWork.handleResult(new BaseCallModel<String>()))
                                                .subscribe(new MyObserver<String>() {
                                                    @Override
                                                    protected void onSuccess(String data, String resultMsg) {

                                                        UiUtils.showToast(getView().getmActivity(), resultMsg);
                                                        getView().addBus(1);
                                                    }

                                                    @Override
                                                    public void onFail(String resultMsg) {
                                                        UiUtils.showToast(getView().getmActivity(), resultMsg);
                                                    }

                                                    @Override
                                                    public void onExit() {
                                                        getView().ExitToLogin();
                                                    }
                                                }));
                                    }
                                });
                            } else {
                                UiUtils.startIntent(getView().getmActivity(), LoginActivity.class);
                            }


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
            if (brand != null) {
                chose.setName(brand);
            } else {
                chose.setName("全部");
            }
            chose.setType("品牌");

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

                        getView().initRvSortType(s.getType(), s.getName());
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
    public RecyclerView.Adapter getRvSortTypeAdapter(final String type, String name) {


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
                        if ("品牌".equals(type)) {
                            lastBrand = s.getName();
                        } else if ("价格".equals(type)) {
                            if (s.getName() != null) {
                                if ("全部".equals(s.getName())) {
                                    lastBeginPrice = null;
                                    lastEndPrice = null;
                                } else if (s.getName().contains("元以上")) {
                                    int indexOf = s.getName().indexOf("元以上");
                                    lastBeginPrice = s.getName().substring(0, indexOf);
                                    lastEndPrice = null;
                                } else {
                                    String[] split = s.getName().split("-");
                                    if (split.length > 1) {
                                        lastBeginPrice = split[0];
                                        lastEndPrice = split[1].substring(0, split[1].indexOf("元"));

                                    }

                                }

                            }

                        }
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
            public void convert(final ViewHolder holder, final Sort s) {
                holder.setText(R.id.tv_item_sort_sort_p, s.getCatalogname());
                setTextColor(s.isSelect(), holder, R.id.tv_item_sort_sort_p, R.color.activityMainPressed, R.color.sortBlue);
                holder.setOnClickListener(R.id.linear_pp_sort, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //记录排序
                        sort = s.getCatalogname();
                        getModel().clear(mSortPList, holder.getAdapterPosition());
                        pangeNo = 1;
                        getShopList();
                        getView().close();
                        // mSortPAdapter.notifyDataSetChanged();
                    }
                });
            }
        };

        return mSortPAdapter;
    }


    /**
     * 获取缓存数据 如:品牌
     */
    public void getCacheData() {
        String string = CacheUtils.getString(getView().getmActivity(), Constants.Cache.PINPAI, "");
        if (TextUtils.isEmpty(string)) {//如果为空,表示没有缓存,需要从服务器去获取
            getView().getRxManager().add(NetWork.getNetService()
                    .getShopBrand()
                    .compose(NetWork.handleResult(new BaseCallModel<List<ShopBrand>>()))
                    .subscribe(new MyObserver<List<ShopBrand>>() {
                        @Override
                        protected void onSuccess(List<ShopBrand> data, String resultMsg) {
                            if (data != null) {
                                List<String> list = new ArrayList<String>();
                                for (ShopBrand s : data) {
                                    if (null != s) {
                                        list.add(s.getBrand());
                                    }
                                }
                                //缓存品牌
                                CacheUtils.putString(getView().getmActivity(), Constants.Cache.PINPAI, GsonTools.getJsonListString(list));
                            }

                        }

                        @Override
                        public void onFail(String resultMsg) {

                        }

                        @Override
                        public void onExit() {

                        }

                    }));
        }
    }

    //加载更多
    @Override
    public void onLoadMore() {
        pangeNo++;
        getShopList();
    }

    //加载错误
    @Override
    public void onLoadError() {
        getView().getLodapger().setLoadVisable();
        pangeNo = 1;
        getShopList();
    }

    @Override
    public void setProduct(String s, String s1) {
        this.productType = s;
        this.brand = s1;
    }


    /**
     * 获取商品类型
     */
    public void getData() {
        getView().getRxManager().add(NetWork.getNetService()
                .getShopTypeList()
                .compose(NetWork.handleResult(new BaseCallModel<List<Sort>>()))
                .subscribe(new MyObserver<List<Sort>>() {
                    @Override
                    protected void onSuccess(List<Sort> data, String resultMsg) {

                        if (mSortList != null && mSortAdapter != null) {
                            mSortList.clear();
                            mSortList.addAll(data);
                            mSortAdapter.notifyDataSetChanged();
                            initNameFirst(data);

                        }

                    }

                    @Override
                    public void onFail(String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("获取商品类型fail:" + resultMsg);
                        }
                        UtilMethod.setLoadpagerError(getView().getLodapger());
                    }

                    @Override
                    public void onExit() {

                    }
                }));


    }

    //获取第一个商品类型的商品
    private void initNameFirst(List<Sort> data) {
        if (data != null && data.size() > 0) {
            if (productType != null) {
                for (int i = 0; i < data.size(); i++) {
                    Sort sort = data.get(i);
                    if (sort != null) {
                        if (productType.equals(sort.getCatalogname())) {
                            data.get(i).setSelect(true);
                        }
                    }
                }
            } else {
                productType = data.get(0).getCatalogname();
                data.get(0).setSelect(true);
            }
            mSortAdapter.notifyDataSetChanged();
            getShopList();
        }

    }

    /**
     * 获取商品列表
     */
    private void getShopList() {


        getView().getRxManager().add(NetWork.getNetService()
                .getShopList(productType, sort, brand, beginPrice, endPrice, pangeNo + "", Constants.PAGESIZE)
                .compose(NetWork.handleResult(new BaseCallModel<List<Shopping>>()))
                .subscribe(new MyObserver<List<Shopping>>() {
                    @Override
                    protected void onSuccess(List<Shopping> data, String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("商品列表:" + data.size());
                        }
                        if (mNameList != null && mNameAdapter != null) {
                            if (pangeNo == 1) {
                                mNameList.clear();
                                getView().getRv().setNoMore(false);
                            } else {
                                UtilMethod.onLoadMore(data,getView().getRv());
                            }
                            mNameList.addAll(data);
                            mNameAdapter.notifyDataSetChanged();
                        }
                        UtilMethod.hideOrEmpty(mNameList, getView().getLodapger());
                    }

                    @Override
                    public void onFail(String resultMsg) {
                        UtilMethod.setLoadpagerError(getView().getLodapger());
                    }

                    @Override
                    public void onExit() {

                    }
                }));
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
    public void refreshChoseRecycleview(String type, String s) {
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
                String string = CacheUtils.getString(getView().getmActivity(), Constants.Cache.PINPAI, "");
                if (!TextUtils.isEmpty(string)) {
                    List<String> listString = GsonParse.getListString(string);
                    if (listString != null) {
                        for (String s : listString) {
                            if (brand != null) {
                                if (brand.equals(s)) {
                                    Type type1 = new Type(s, true);
                                    mTypeList.add(type1);

                                } else {
                                    Type type1 = new Type(s, false);
                                    mTypeList.add(type1);
                                }
                            } else {
                                //默认设置全部 为选中
                                if ("全部".equals(s)) {
                                    Type type1 = new Type(s, true);
                                    mTypeList.add(type1);
                                } else {
                                    Type type1 = new Type(s, false);
                                    mTypeList.add(type1);
                                }
                            }

                        }
                    } else {
                        mTypeList.add(new Type("全部", true));
                    }
                } else {
                    mTypeList.add(new Type("全部", true));
                }

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
        if (!TextUtils.isEmpty(getView().getStratPrice()) && !TextUtils.isEmpty(getView().getEndPrice())) {
            lastBeginPrice = getView().getStratPrice();
            lastEndPrice = getView().getEndPrice();
            refreshTypeState(null, "价格");
            refreshChoseRecycleview("价格", lastBeginPrice + "-" + lastEndPrice + "元");
        }
    }

    /**
     * 清空筛选
     */
    @Override
    public void clearChose() {
        refreshChoseRecycleview(null, "全部");
        beginPrice = null;
        endPrice = null;
        brand = null;
    }

    /**
     * 确认筛选
     */
    @Override
    public void comfirmChose() {
        brand = lastBrand;
        beginPrice = lastBeginPrice;
        endPrice = lastEndPrice;
        pangeNo = 1;
        getShopList();
        getView().close();
    }

    /**
     * 如果是没有点击确定关闭筛选的就将数据还原
     */
    @Override
    public void reFreshChose(int pressNum) {
        if (pressNum == 1) {
            if (mChoseList != null) {
                for (int i = 0; i < mChoseList.size(); i++) {
                    //还原来筛选
                    Chose chose = mChoseList.get(i);
                    if (chose != null) {
                        if ("品牌".equals(chose.getType())) {
                            if (brand != null) {
                                refreshChoseRecycleview(chose.getType(), brand);
                            } else {
                                refreshChoseRecycleview(chose.getType(), "全部");
                            }
                        } else if ("价格".equals(chose.getType())) {
                            if (!TextUtils.isEmpty(beginPrice) && !TextUtils.isEmpty(endPrice)) {
                                refreshChoseRecycleview(chose.getType(), beginPrice + "-" + endPrice + "元");
                            } else if (!TextUtils.isEmpty(beginPrice) && TextUtils.isEmpty(endPrice)) {
                                refreshChoseRecycleview(chose.getType(), beginPrice + "元以上");
                            } else {
                                refreshChoseRecycleview(chose.getType(), "全部");
                            }
                        }
                        if (map != null) {
                            //还原条件
                            List<Type> types = map.get(chose.getType());
                            if (types != null) {

                                for (Type type : types) {
                                    if (type != null) {
                                        if (chose.getName().equals(type.getName())) {
                                            type.setSelect(true);
                                        } else {
                                            type.setSelect(false);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

                lastEndPrice = endPrice;
                lastBeginPrice = beginPrice;
                lastBrand = brand;
            }
        }


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

        clear();
    }


}