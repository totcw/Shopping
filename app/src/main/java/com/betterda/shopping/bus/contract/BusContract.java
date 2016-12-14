package com.betterda.shopping.bus.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/13.
 */

public class BusContract {

    public interface View extends IView{
        void initRvBus(RecyclerView.Adapter adapter);
        /**
         * 改变结算和删除的状态
         *
         * @param change2
         */
        void setIvDeleteSelect(boolean change2);
        /**
         * 改变全选的状态
         * @param change
         */
        void setIvAllSelect(boolean change);


        void setActionText(String status);
        /**
         * 更新合计
         * @param money
         */
        void setHejiText(String money);
        //显示结算还删除
        void setStatusVisable(boolean isEdit);
    }

    public interface Presenter extends IPresenter<View>{
        void edit();

        void delete();

        void jiesuan();

        void checkAll();
    }

    public interface Model extends IModel {
    }


}