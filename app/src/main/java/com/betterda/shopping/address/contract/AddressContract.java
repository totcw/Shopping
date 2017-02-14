package com.betterda.shopping.address.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.javabean.Address;
import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class AddressContract {
    
public interface View extends IView{
    void initRv(RecyclerView.Adapter adapter);
}

public interface Presenter extends IPresenter<View>{

    void getData();

    void onError();
}

public interface Model extends IModel{
    void setAddressList(List<Address> list);
}


}