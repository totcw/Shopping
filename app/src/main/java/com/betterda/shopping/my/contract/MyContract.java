package com.betterda.shopping.my.contract;

import android.widget.ImageView;
import android.widget.TextView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/8.
 */

public class MyContract {
    
public interface View extends IView{
    TextView getTextViewNumber();

    ImageView getSimpleDraweeView();
}

public interface Presenter extends IPresenter<View>{
    void onStart();
}

public interface Model extends IModel{
}


}