package com.betterda.shopping.ziti.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;


/**
 * Created by Administrator on 2016/12/29.
 */

public class ZiTiContract {

    public interface View extends IView {
    }

    public interface Presenter extends IPresenter<View> {
        RecyclerView.Adapter getRvAdapter();

        void onError();

        void onStart();
    }

    public interface Model extends IModel {
    }


}