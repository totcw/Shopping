package com.betterda.shopping.shouye.contract;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.betterda.shopping.base.IModel;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.base.IView;
import com.betterda.shopping.shouye.model.LunBoTu;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ShouYeContract {
    
public interface View extends IView{
    //初始化轮播图的viewpage
    void initVpLunbotu(PagerAdapter adapter);

    ViewGroup getLpoint();

    ViewPager getViewPager();
}

public interface Presenter extends IPresenter<View>{

    //获取轮播图的数据
    List<LunBoTu> getLunBoTuList();
    //创建轮播图的点
    void cratePoint();

    void createHandler();
    //创建轮播图的view
    android.view.View ctreaImageView(int position);
}

public interface Model extends IModel{

}


}