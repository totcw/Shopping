package com.betterda.shopping.wallet.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.mylibrary.xrecycleview.XRecyclerView;
import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.javabean.TitleBean;
import com.betterda.shopping.javabean.MingXi;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MingXiContract  {
    
public interface View extends IView{
    void initRv(RecyclerView.Adapter adapter);

    XRecyclerView getRv();
}

public interface Presenter extends IPresenter<View>{
    RecyclerView.ItemDecoration getItemDecoration();

    void onError();

    void onLoadMore();
}

public interface Model extends IModel{
    void getList(List<TitleBean<MingXi>> list);
}


}