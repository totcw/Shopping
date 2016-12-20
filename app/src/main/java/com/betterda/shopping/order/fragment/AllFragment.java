package com.betterda.shopping.order.fragment;

import com.betterda.shopping.order.model.OrderAll;

/**
 * 全部订单
 * Created by Administrator on 2016/12/19.
 */

public class AllFragment extends BaseOrderFragment {

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void getData() {
        super.getData();
        for ( int i=0;i<3;i++) {
            mOrderAllList.add(new OrderAll());
        }
        mOrderAllCommonAdapter.notifyDataSetChanged();
    }
}
