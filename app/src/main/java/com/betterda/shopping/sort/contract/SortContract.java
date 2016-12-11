package com.betterda.shopping.sort.contract;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/8.
 */

public class SortContract {
    
public interface View extends IView{
    //初始化类别的recycleview
    void initRvSortSort(RecyclerView.Adapter adapter);

    void initRvSortName(RecyclerView.Adapter adapter);

    void initRvSortType(String type);



    //判断popupwindow是否关闭
    boolean close();

    /**
     * 显示筛选popupwindow的条件界面
     */
    void showType(boolean isShow);

    String getStratPrice();

    String getEndPrice();
}

public interface Presenter extends IPresenter<View>{


    void loadShopping();


    void showShopping(boolean isShow, RecyclerView mRvSortName);

    int getPosition(int position);

    RecyclerView.Adapter getRvSortChoseAdapter();

    RecyclerView.Adapter getRvSortTypeAdapter(String type);

    void clear();
    //价格确认
    void priceComfirm();
}

public interface Model extends IModel{
}


}