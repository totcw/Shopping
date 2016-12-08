package com.betterda.shopping.sort;

import android.view.LayoutInflater;
import android.view.View;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.sort.contract.SortContract;
import com.betterda.shopping.sort.presenter.SortPresenterImpl;

/**
 * Created by Administrator on 2016/12/8.
 */

public class SortFragment extends BaseFragment<SortContract.Presenter>implements SortContract.View {


    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_sort,null);
    }

    @Override
    protected SortContract.Presenter onLoadPresenter() {
        return new SortPresenterImpl();
    }
}
