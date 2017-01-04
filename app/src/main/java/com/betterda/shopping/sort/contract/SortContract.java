package com.betterda.shopping.sort.contract;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.sort.model.Sort;
import com.betterda.shopping.sort.model.Type;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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

    /**
     * 获取开始价格
     * @return
     */
    String getStratPrice();

    String getEndPrice();

    String getBusText();
    void addBus(int amount);
    //获取当前页面的根布局
    ViewGroup getViewgroup();
    //获取购物车的view
    android.view.View getBusView();
}

public interface Presenter extends IPresenter<View>{


    /**
     * 获取筛选的rv的适配器
     * @return
     */
    RecyclerView.Adapter getRvSortChoseAdapter();

    /**
     * 获取筛选的条件的rv的适配器
     * @param type
     * @return
     */
    RecyclerView.Adapter getRvSortTypeAdapter(String type);

    /**
     * 获取排序的rv的适配器
     * @return
     */
    RecyclerView.Adapter getRvSortNameSortAdapter();

    /**
     * 情况数据
     */
    void clear();
    //价格确认
    void priceComfirm();

    /**
     * 清空筛选条件
     */
    void clearChose();

    /**
     * 筛选的确认
     */
    void comfirmChose();

}

public interface Model extends IModel{
    /**
     * 给排序,类别的容器添加数据
     * @param list
     */
    void  addSort(List<Sort> list);

    void clear(List<Sort> mSortList, int adapterPosition);
}


}