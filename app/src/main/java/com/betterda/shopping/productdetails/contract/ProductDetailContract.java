package com.betterda.shopping.productdetails.contract;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.productdetails.model.Share;
import com.betterda.shopping.widget.AddAndSub;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class ProductDetailContract {

    public interface View extends IView{
        //关闭popupwindow
        void close();

        TextView getTvBus();

        AddAndSub getMaddAndSub();
    }

    public interface Presenter extends IPresenter<View>{

        RecyclerView.Adapter getRvShareAdapter();

        void addBus();
    }

    public interface Model extends IModel{
        List<Share> getShareList();
    }


}