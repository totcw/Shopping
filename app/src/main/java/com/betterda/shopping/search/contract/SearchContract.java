package com.betterda.shopping.search.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/15.
 */

public class SearchContract {

    public interface View extends IView {
    }

    public interface Presenter  extends IPresenter<View>{
        RecyclerView.Adapter getRvSearchJiluAdapter();

        RecyclerView.Adapter getRvSearchRemenAdapter();
    }

    public interface Model extends IModel{
    }


}