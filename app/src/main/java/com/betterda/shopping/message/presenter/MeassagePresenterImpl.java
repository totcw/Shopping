package com.betterda.shopping.message.presenter;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.message.contract.MeassageContract;
import com.betterda.shopping.message.model.MeassageContent;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Administrator on 2017/01/03
*/

public class MeassagePresenterImpl  extends BasePresenter<MeassageContract.View,MeassageContract.Model> implements MeassageContract.Presenter{
    private List<MeassageContent> meassageContentList;
    private CommonAdapter<MeassageContent> contentCommonAdapter;

    @Override
    public void start() {
        initRv();
    }

    private void initRv() {
        meassageContentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            meassageContentList.add(new MeassageContent());
        }
        contentCommonAdapter = new CommonAdapter<MeassageContent>(getView().getmActivity(), R.layout.item_rv_meassagecontent,meassageContentList) {
            @Override
            public void convert(ViewHolder holder, MeassageContent meassageContent) {

            }
        };
        getView().initRv(contentCommonAdapter);
    }

    @Override
    public void destroy() {

    }
}