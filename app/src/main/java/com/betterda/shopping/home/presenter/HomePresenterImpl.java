package com.betterda.shopping.home.presenter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BasePresenter;
import com.betterda.shopping.find.FindFragment;
import com.betterda.shopping.home.contract.HomeContract;
import com.betterda.shopping.home.contract.HomeContract.Presenter;
import com.betterda.shopping.my.MyFragment;
import com.betterda.shopping.shouye.ShouYeFragment;
import com.betterda.shopping.sort.SortFragment;

/**
* Created by Administrator on 2016/12/05
*/

public class HomePresenterImpl extends BasePresenter<HomeContract.View,HomeContract.Model> implements HomeContract.Presenter {

    private ShouYeFragment mShouYeFragment;
    private SortFragment mSortFragment;
    private FindFragment mFindFragment;
    private MyFragment  mMyFragment;
    private FragmentManager mFragmentManager;

    @Override
    public void start() {
        initFragment();

    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentActivity mFragmentActivity = (FragmentActivity) getView().getmActivity();
        mFragmentManager = mFragmentActivity.getSupportFragmentManager();

        mShouYeFragment = new ShouYeFragment();
        mSortFragment = new SortFragment();
        mFindFragment = new FindFragment();
        mMyFragment = new MyFragment();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!getView().getmActivity().isFinishing()) {
            fragmentTransaction.add(R.id.frame_activity_main, mShouYeFragment).add(R.id.frame_activity_main, mSortFragment)
                    .add(R.id.frame_activity_main, mFindFragment).add(R.id.frame_activity_main,mMyFragment);

            fragmentTransaction.commitAllowingStateLoss();
        }

      switchFragmentTo(mShouYeFragment);
    }

    /**
     * 切换到对应的fragment
     * @param fragment
     */
    public void switchFragmentTo(Fragment fragment) {
        if (null != mFragmentManager) {
            FragmentTransaction fragmentTransaction2 = mFragmentManager.beginTransaction();
            if (null != fragmentTransaction2 && getView().getmActivity() != null) {

                if (!getView().getmActivity().isFinishing()) {
                    fragmentTransaction2.hide(mShouYeFragment);
                    fragmentTransaction2.hide(mSortFragment);
                    fragmentTransaction2.hide(mFindFragment);
                    fragmentTransaction2.hide(mMyFragment);
                    fragmentTransaction2.show(fragment);
                    fragmentTransaction2.commitAllowingStateLoss();
                }
            }
        }
    }

    /**
     * 切换到对应的fragment
     * @param id
     */
    @Override
    public void switchToFragment(int id) {
        switch (id) {
            case R.id.idv_activity_main_shouye:

                switchFragmentTo(mShouYeFragment);

                break;
            case R.id.idv_activity_main_sort:

                switchFragmentTo(mSortFragment);

                break;
            case R.id.idv_activity_main_find:

                switchFragmentTo(mFindFragment);

                break;
            case R.id.idv_activity_main_my:
                switchFragmentTo(mMyFragment);

                break;
        }
    }

    @Override
    public void destroy() {

    }


}