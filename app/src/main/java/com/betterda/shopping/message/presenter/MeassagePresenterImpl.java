package com.betterda.shopping.message.presenter;

import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.message.contract.MeassageContract;
import com.betterda.shopping.javabean.MeassageContent;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.NetworkUtils;
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
    private int pageNo=1;

    @Override
    public void start() {
        initRv();
        getData();
    }


    private void initRv() {
        meassageContentList = new ArrayList<>();

        contentCommonAdapter = new CommonAdapter<MeassageContent>(getView().getmActivity(), R.layout.item_rv_meassagecontent,meassageContentList) {
            @Override
            public void convert(ViewHolder holder, MeassageContent meassageContent) {
                if (meassageContent != null) {
                    holder.setText(R.id.tv_item_meassagecontent_time, meassageContent.getMsgTime());
                    holder.setText(R.id.tv_item_meassagecontent_title, meassageContent.getTitle());
                    holder.setText(R.id.tv_item_meassagecontent_content, meassageContent.getContent());
                }
            }
        };
        getView().initRv(contentCommonAdapter);
    }


    private void getData() {
        NetworkUtils.isNetWork(getView().getmActivity(), getView().getLodapger(), new NetworkUtils.SetDataInterface() {
            @Override
            public void getDataApi() {
                getView().getRxManager().add(NetWork.getNetService()
                .getMeassage(getView().getAccount(),getView().getToken(),pageNo+"", Constants.PAGESIZE)
                .compose(NetWork.handleResult(new BaseCallModel<List<MeassageContent>>()))
                .subscribe(new MyObserver<List<MeassageContent>>() {
                    @Override
                    protected void onSuccess(List<MeassageContent> data, String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("消息列表success:"+data.toString());
                        }
                        if (data != null) {
                            if (pageNo == 1) {
                                meassageContentList.clear();
                            }
                            meassageContentList.addAll(data);
                            contentCommonAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFail(String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("消息列表fail:"+resultMsg);
                        }
                    }

                    @Override
                    public void onExit() {

                    }
                }));
            }
        });
    }



    @Override
    public void destroy() {

    }
}