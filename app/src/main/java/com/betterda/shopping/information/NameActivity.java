package com.betterda.shopping.information;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;

/**
 * Created by Administrator on 2016/12/19.
 */

public class NameActivity extends BaseActivity {
    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_name);
    }
}
