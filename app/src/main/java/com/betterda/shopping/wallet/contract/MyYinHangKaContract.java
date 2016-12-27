package com.betterda.shopping.wallet.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.mylibrary.LoadingPager;
import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.widget.NormalTopBar;

/**
 * Created by Administrator on 2016/12/27.
 */

public class MyYinHangKaContract {

    public interface View extends IView{
        NormalTopBar getNormalTopBar();

        LoadingPager getLoadpager();
    }

    public interface Presenter extends IPresenter<View> {
        RecyclerView.Adapter getRvAdapter();
    }

    public interface Model extends IModel {
    }


}