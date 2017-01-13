package com.betterda.shopping.wallet;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.data.BankData;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.wallet.contract.TiXianContract;
import com.betterda.shopping.wallet.presenter.TiXianPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现
 * Created by Administrator on 2016/12/28.
 */

public class TiXianActivity extends BaseActivity<TiXianContract.Presenter> implements TiXianContract.View {
    @BindView(R.id.topbar_yetixian)
    NormalTopBar mTopbarYetixian;
    @BindView(R.id.tv_yrtixian_money)
    TextView mTvYrtixianMoney;//可提现余额
    @BindView(R.id.iv_tixian_icon)
    ImageView mIvTixianIcon;
    @BindView(R.id.tv_tixian_bank)
    TextView mTvTixianBank;//所属银行
    @BindView(R.id.tv_tixian_bankcard)
    TextView mTvTixianBankcard;//银行卡号
    @BindView(R.id.relative_tixian_chose)
    RelativeLayout relativeTixianChose;
    @BindView(R.id.et_tixian_money)
    EditText mEtTixianMoney;
    @BindView(R.id.linear_tixian)
    LinearLayout mLinearChoseBank;//选择银行卡

    @Override
    protected TiXianContract.Presenter onLoadPresenter() {
        return new TiXianPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_tixian);
    }

    @Override
    public void init() {
        super.init();
        mTopbarYetixian.setTitle("提现");
    }

    @OnClick({R.id.relative_tixian_tiqu, R.id.iv_chongzhi_delete, R.id.btn_tixian,
            R.id.bar_back,R.id.linear_tixian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_tixian_tiqu://全部提取
                getPresenter().getAll();
                break;
            case R.id.iv_chongzhi_delete:
                mEtTixianMoney.setText("");
                break;
            case R.id.linear_tixian://选择银行卡
                Intent intent = new Intent(getmActivity(), MyYinHangKaActivity.class);
                intent.putExtra("tixian", true);
                UiUtils.startIntentForResult(getmActivity(),intent,0);
                break;
            case R.id.btn_tixian:
                getPresenter().commit();
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            String bank = data.getStringExtra("bank");
            String bankCrad = data.getStringExtra("bankCard");
            String id = data.getStringExtra("id");
            getPresenter().setBank(bank, id);
           // System.out.println(id);
            if (relativeTixianChose != null) {
                relativeTixianChose.setVisibility(View.INVISIBLE);
            }
            if (mTvTixianBank != null) {
                mTvTixianBank.setText(bank);

            }
            if (mTvTixianBankcard != null) {
                mTvTixianBankcard.setText(bankCrad);

            }
            if (mIvTixianIcon != null) {
                if (BankData.getBank(bank) != -1) {
                    mIvTixianIcon.setImageResource(BankData.getBank(bank));
                }

            }
        }
    }

    @Override
    public String getMoney() {
        return mEtTixianMoney.getText().toString().trim();
    }

    @Override
    public TextView getTvBalance() {
        return mTvYrtixianMoney;
    }

}
