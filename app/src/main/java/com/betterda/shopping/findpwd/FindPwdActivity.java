package com.betterda.shopping.findpwd;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.findpwd.contract.FindPwdContract;
import com.betterda.shopping.findpwd.presenter.FindPwdPresenterImpl;

/**
 * Created by Administrator on 2016/12/20.
 */

public class FindPwdActivity extends BaseActivity<FindPwdContract.Presenter> implements FindPwdContract.View {
    @Override
    protected FindPwdContract.Presenter onLoadPresenter() {
        return new FindPwdPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_findpwd);
    }
}
