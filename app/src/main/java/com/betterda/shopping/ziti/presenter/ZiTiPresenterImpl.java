package com.betterda.shopping.ziti.presenter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.ZItiMa;
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.ziti.EweiMaActivity;
import com.betterda.shopping.ziti.contract.ZiTiContract;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Administrator on 2016/12/29
*/

public class ZiTiPresenterImpl  extends BasePresenter<ZiTiContract.View,ZiTiContract.Model> implements ZiTiContract.Presenter{
    private List<ZItiMa> mZiTiList;
    private CommonAdapter<ZItiMa> mZiTiCommonAdapter;
    @Override
    public void start() {
        getData();
    }


    @Override
    public RecyclerView.Adapter getRvAdapter() {
        mZiTiList = new ArrayList<>();

        mZiTiCommonAdapter = new CommonAdapter<ZItiMa>(getView().getmActivity(), R.layout.item_recyclevew_kaquan,mZiTiList) {
            @Override
            public void convert(ViewHolder holder, ZItiMa ziTi) {
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

    private void getData() {
        NetworkUtils.isNetWork(getView().getmActivity(), getView().getLodapger(), new NetworkUtils.SetDataInterface() {
            @Override
            public void getDataApi() {
                System.out.println("自提码");
                getView().getRxManager().add(NetWork.getNetService()
                .getZiTiMa(getView().getAccount(),getView().getToken())
                .compose(NetWork.handleResult(new BaseCallModel<List<ZItiMa>>()))
                .subscribe(new MyObserver<List<ZItiMa>>() {
                    @Override
                    protected void onSuccess(List<ZItiMa> data, String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("自提码success:"+data.toString());
                        }
                        UiUtils.showToast(getView().getmActivity(),data.toString());
                    }

                    @Override
                    public void onFail(String resultMsg) {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("自提码fail:"+resultMsg);
                        }
                    }

                    @Override
                    public void onExit() {
                        if (BuildConfig.LOG_DEBUG) {
                            System.out.println("token失效");
                        }
                    }
                }));
            }
        });
    }



    @Override
    public void destroy() {

    }


}