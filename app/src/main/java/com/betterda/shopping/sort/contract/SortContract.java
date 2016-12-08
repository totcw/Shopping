package com.betterda.shopping.sort.contract;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/8.
 */

public class SortContract {
    
public interface View extends IView{
}

public interface Presenter extends IPresenter<View>{
}

public interface Model extends IModel{
}


}