package com.betterda.shopping.tuijian.contract;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.productdetails.model.Share;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class LiJiTuijianContract {

    public interface View extends IView {
        void close();

        void setUrl(String url);

        android.view.View getImageview();
    }

    public interface Presenter extends IPresenter<View> {
        RecyclerView.Adapter getRvShareAdapter();

        void onError();
    }

    public interface Model extends IModel {
        List<Share> getShareList();
    }


}