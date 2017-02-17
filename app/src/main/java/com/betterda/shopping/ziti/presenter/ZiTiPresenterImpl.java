package com.betterda.shopping.ziti.presenter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betterda.shopping.BuildConfig;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.ZItiMa;
import com.betterda.shopping.order.OrderDetailActivity;
import com.betterda.shopping.utils.ImageTools;
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;
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

    }


    @Override
    public RecyclerView.Adapter getRvAdapter() {
        mZiTiList = new ArrayList<>();

        mZiTiCommonAdapter = new CommonAdapter<ZItiMa>(getView().getmActivity(), R.layout.item_recyclevew_kaquan,mZiTiList) {
            @Override
            public void convert(ViewHolder holder, final ZItiMa ziTi) {
                if (ziTi != null) {
                    holder.setText(R.id.tv_tiem_hexiao_kahao, ziTi.getBarCode());
                    holder.setText(R.id.tv_item_hexiao_time, ziTi.getTime());
                    Bitmap bitmap = ImageTools.generateQRCode(ziTi.getBarCode()+","+ziTi.getOrderNum(), getView().getmActivity());
                    holder.setImageBitmap(R.id.iv_kaquan, bitmap);
                    holder.setOnClickListener(R.id.relative_item_kaquan, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getView().getmActivity(), EweiMaActivity.class);
                            intent.putExtra("bianhao", ziTi.getBarCode());
                            intent.putExtra("orderId", ziTi.getOrderNum());
                            UiUtils.startIntent(getView().getmActivity(), intent);
                        }
                    });
                    holder.setOnClickListener(R.id.linear_kaquan, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getView().getmActivity(), OrderDetailActivity.class);
                            intent.putExtra("orderId", ziTi.getOrderNum());
                            UiUtils.startIntent(getView().getmActivity(), intent);
                        }
                    });
                }
            }
        };
        return mZiTiCommonAdapter;
    }

    @Override
    public void onError() {
        getData();
    }

    @Override
    public void onStart() {
        getData();
    }

    private void getData() {
        getView().getLodapger().setLoadVisable();
        NetworkUtils.isNetWork(getView().getmActivity(), getView().getLodapger(), new NetworkUtils.SetDataInterface() {
            @Override
            public void getDataApi() {
                NetworkUtils.isNetWork(getView().getmActivity(), getView().getLodapger(), new NetworkUtils.SetDataInterface() {
                    @Override
                    public void getDataApi() {
                        getView().getRxManager().add(NetWork.getNetService()
                                .getZiTiMa(getView().getAccount(),getView().getToken())
                                .compose(NetWork.handleResult(new BaseCallModel<List<ZItiMa>>()))
                                .subscribe(new MyObserver<List<ZItiMa>>() {
                                    @Override
                                    protected void onSuccess(List<ZItiMa> data, String resultMsg) {
                                        if (BuildConfig.LOG_DEBUG) {
                                            System.out.println("自提码success:"+data.toString());
                                        }
                                        if (mZiTiList != null) {
                                            mZiTiList.clear();
                                            mZiTiList.addAll(data);
                                        }
                                        if (mZiTiCommonAdapter != null) {
                                            mZiTiCommonAdapter.notifyDataSetChanged();
                                        }
                                        UtilMethod.hideOrEmpty(data,getView().getLodapger());
                                       // UiUtils.showToast(getView().getmActivity(),data.toString());
                                    }

                                    @Override
                                    public void onFail(String resultMsg) {
                                        if (BuildConfig.LOG_DEBUG) {
                                            System.out.println("自提码fail:"+resultMsg);
                                        }
                                        getView().getLodapger().setErrorVisable();
                                    }

                                    @Override
                                    public void onExit() {
                                        if (BuildConfig.LOG_DEBUG) {
                                            System.out.println("token失效");
                                        }
                                        getView().ExitToLogin();
                                    }
                                }));
                    }
                });

            }
        });
    }



    @Override
    public void destroy() {

    }


}