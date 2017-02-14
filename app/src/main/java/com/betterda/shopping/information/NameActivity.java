package com.betterda.shopping.information;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.betterda.mylibrary.ShapeLoadingDialog;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.http.MyObserver;
import com.betterda.shopping.http.NetWork;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.utils.CacheUtils;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.utils.NetworkUtils;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改昵称
 * Created by Administrator on 2016/12/19.
 */

public class NameActivity extends BaseActivity {
    @BindView(R.id.topbar_name)
    NormalTopBar mTopbarName;
    @BindView(R.id.et_name_name)
    EditText etNameName;
    private int amount;

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_name);
    }

    @Override
    public void init() {
        super.init();
        mTopbarName.setTitle("修改昵称");
        etNameName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //start表示在输入这次的数之前有几个数了
                amount = start + after;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.btn_editname_name, R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;
            case R.id.btn_editname_name:
                String name = etNameName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    UiUtils.showToast(this,"昵称不能为空");
                    return;
                }
                if (amount > 8) {
                    UiUtils.showToast(this,"昵称不能超过8个字");
                    return;
                }
                //检测只能含有英文,数字,中文
                boolean is = name.matches("^[a-z0-9A-Z\\u4e00-\\u9fa5]+$");
                if (!is) {
                    UiUtils.showToast(this,"含有非法字符");
                    return;
                }
                getData(name);
                break;

        }
    }

    private void getData(final String name) {
        NetworkUtils.isNetWork(getmActivity(), etNameName, new NetworkUtils.SetDataInterface() {
            @Override
            public void getDataApi() {
                final ShapeLoadingDialog dialog = UiUtils.createDialog(getmActivity(), "正在提交...");
                UiUtils.showDialog(getmActivity(),dialog);
                getRxManager().add(NetWork.getNetService()
                .getPwdUpdate(getAccount(),null,name,null)
                .compose(NetWork.handleResult(new BaseCallModel<String>()))
                .subscribe(new MyObserver<String>() {
                    @Override
                    protected void onSuccess(String data, String resultMsg) {
                        UiUtils.showToast(getmActivity(),resultMsg);
                        UiUtils.dissmissDialog(getmActivity(),dialog);
                        //缓存昵称
                        CacheUtils.putString(getmActivity(), getAccount() + Constants.Cache.NICKNAME, name);
                        finish();
                    }

                    @Override
                    public void onFail(String resultMsg) {
                        UiUtils.dissmissDialog(getmActivity(),dialog);
                        UiUtils.showToast(getmActivity(),resultMsg);
                    }

                    @Override
                    public void onExit() {
                        UiUtils.dissmissDialog(getmActivity(),dialog);
                        ExitToLogin();
                    }
                }));
            }
        });


    }
}
