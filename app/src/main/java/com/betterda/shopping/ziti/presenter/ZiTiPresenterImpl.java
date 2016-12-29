package com.betterda.shopping.ziti.presenter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.ziti.EweiMaActivity;
import com.betterda.shopping.ziti.contract.ZiTiContract;
import com.betterda.shopping.ziti.model.ZiTi;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Administrator on 2016/12/29
*/

public class ZiTiPresenterImpl  extends BasePresenter<ZiTiContract.View,ZiTiContract.Model> implements ZiTiContract.Presenter{
    private List<ZiTi> mZiTiList;
    private CommonAdapter<ZiTi> mZiTiCommonAdapter;
    @Override
    public void start() {

    }
    @Override
    public RecyclerView.Adapter getRvAdapter() {
        mZiTiList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mZiTiList.add(new ZiTi());
        }
        mZiTiCommonAdapter = new CommonAdapter<ZiTi>(getView().getmActivity(), R.layout.item_recyclevew_kaquan,mZiTiList) {
            @Override
            public void convert(ViewHolder holder, ZiTi ziTi) {
                if (ziTi != null) {
                    holder.setOnClickListener(R.id.relative_item_kaquan, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UiUtils.startIntent(getView().getmActivity(), EweiMaActivity.class);
                        }
                    });
                }
            }
        };
        return mZiTiCommonAdapter;
    }
    @Override
    public void destroy() {

    }


}