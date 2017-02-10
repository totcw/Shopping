package com.betterda.shopping.shouye.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.betterda.shopping.shouye.contract.ShouYeContract;
import com.betterda.shopping.javabean.LunBoTu;

import java.util.List;

/**
 * 轮播图的适配器
 * Created by Administrator on 2016/4/21.
 */
public class LunBoTuAdapter extends PagerAdapter {

    private List<LunBoTu> list;
    private ShouYeContract.Presenter mPresenter;

    public LunBoTuAdapter(ShouYeContract.Presenter iShouyePresenter) {
        this.mPresenter = iShouyePresenter;
        list = mPresenter.getLunBoTuList();

        mPresenter.cratePoint();
        mPresenter.createHandler();

    }

    @Override
    public int getCount() {
     /*   if (null != list) {
            return list.size();
        } else {
            return 0;
        }*/
       return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (list==null||list.size() == 0) {
            return null;
        }
        position %= list.size();
        if (position < 0) {

            position = list.size() + position;

        }

        if (null != mPresenter) {
            View view = mPresenter.ctreaImageView(position);
            if (null != view) {

                container.addView(view);
                return view;
            }
        }

        return null;

    }

}
