package com.betterda.shopping.productdetails.contract;

import android.support.v7.widget.RecyclerView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.productdetails.model.Share;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class ProductDetailContract {

    public interface View extends IView{
        //关闭popupwindow
        void close();
    }

    public interface Presenter extends IPresenter<View>{

        RecyclerView.Adapter getRvShareAdapter();
    }

    public interface Model extends IModel{
        List<Share> getShareList();
    }


}