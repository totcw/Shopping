package com.betterda.shopping.find;

import android.view.LayoutInflater;
import android.view.View;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.find.contract.FindContract;
import com.betterda.shopping.find.presenter.FindPresenterImpl;

/**
 * Created by Administrator on 2016/12/8.
 */

public class FindFragment extends BaseFragment<FindContract.Presenter> implements FindContract.View {


    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_find,null);
    }

    @Override
    protected FindContract.Presenter onLoadPresenter() {
        return new FindPresenterImpl();
    }
}
