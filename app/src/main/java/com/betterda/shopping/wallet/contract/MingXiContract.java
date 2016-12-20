package com.betterda.shopping.wallet.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.wallet.model.MingXi;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MingXiContract  {
    
public interface View extends IView{
    void initRv(RecyclerView.Adapter adapter);
}

public interface Presenter extends IPresenter<View>{
}

public interface Model extends IModel{
    void getList(List<MingXi> list);
}


}