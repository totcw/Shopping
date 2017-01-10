package com.betterda.shopping.address.contract;

import android.view.View;
import android.widget.TextView;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;

/**
 * Created by Administrator on 2016/12/16.
 */

public class EditAddressContract {

    public interface View extends IView{
        String getNameText();
        String getNumberText();

        String getAreaText();

        String getaddressText();

        void setValue(String name, String number, String ares, String address, boolean isDefault);

        TextView getTv();
    }

    public interface Presenter extends IPresenter<View>{
        void onToggle(boolean on);
        //保存
        void save();
        //删除
        void delete();
    }

    public interface Model extends IModel{
    }


}