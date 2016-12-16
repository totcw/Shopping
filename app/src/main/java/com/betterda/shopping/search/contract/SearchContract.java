package com.betterda.shopping.search.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.widget.BusView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

/**
 * Created by Administrator on 2016/12/15.
 */

public class SearchContract {

    public interface View extends IView {
        LoadingPager getLoadpager();

        BusView getmBusView();

        /**
         * 设置rv 是否可见
         * @param b
         */
        void setRvSearchVisable(boolean b);

        /**
         * 设置搜索栏的值
         * @param s
         */
        void setEtSearch(String s);
    }

    public interface Presenter  extends IPresenter<View>{

        TagAdapter getFlowRemenAdapter();
        TagAdapter getFlowJiluAdapter();

        /**
         * flowlayout热门的点击事件
         * @param view
         * @param position
         * @param parent
         */
        void onTagClickListener(android.view.View view, int position, FlowLayout parent);
        /**
         * flowlayout记录的点击事件
         * @param view
         * @param position
         * @param parent
         */
        void onJiluTagClickListener(android.view.View view, int position, FlowLayout parent);

        /**
         * 删除搜索记录
         */
        void deleteJilu();


        RecyclerView.Adapter getRvSearchAdapter();

        /**
         * 开启搜索
         * @param content
         */
        void load(String content);
    }

    public interface Model extends IModel{

    }


}