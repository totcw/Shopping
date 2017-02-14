package com.betterda.shopping.message.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;


/**
 * Created by Administrator on 2017/1/3.
 */

public class MeassageContract {

    public interface View extends IView {
        void initRv(RecyclerView.Adapter adapter);
    }

    public interface Presenter extends IPresenter<View> {
        void onError();
    }

    public interface Model extends IModel {
    }


}