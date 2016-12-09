package com.betterda.shopping.home.contract;

import android.support.v4.app.Fragment;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * home的mvp接口管理
 * Created by Administrator on 2016/12/5.
 */

public class HomeContract {
    
public interface View extends IView{
}

public interface  Presenter extends IPresenter<View>{

    //切换到对应的fragment
    void switchToFragment(int id);
    //检测popupwindow是否关闭
    boolean checkPopWindow();
}

public interface Model extends IModel{
}


}