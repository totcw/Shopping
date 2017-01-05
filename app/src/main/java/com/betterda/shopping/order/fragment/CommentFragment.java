package com.betterda.shopping.order.fragment;

import com.betterda.shopping.javabean.OrderAll;

/**
 * 评价订单
 * Created by Administrator on 2016/12/19.
 */

public class CommentFragment extends BaseOrderFragment {

    @Override
    public void getData() {
        super.getData();
        for ( int i=0;i<5;i++) {
            mOrderAllList.add(new OrderAll());
        }
        mOrderAllCommonAdapter.notifyDataSetChanged();
    }
}
