package com.betterda.shopping.information;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/19.
 */

public class NameActivity extends BaseActivity {
    @BindView(R.id.topbar_name)
    NormalTopBar mTopbarName;
    @BindView(R.id.et_name_name)
    EditText etNameName;


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
    }

    @OnClick({R.id.btn_editname_name, R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                back();
                break;
            case R.id.btn_editname_name:

                break;

        }
    }
}
