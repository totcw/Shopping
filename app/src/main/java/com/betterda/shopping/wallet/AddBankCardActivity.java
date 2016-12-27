package com.betterda.shopping.wallet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.data.BankData;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.wallet.contract.AddBankCardContract;
import com.betterda.shopping.wallet.presenter.AddBankCardPresenterImpl;
import com.betterda.shopping.widget.NormalTopBar;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加银行卡
 * Created by Administrator on 2016/12/27.
 */

public class AddBankCardActivity extends BaseActivity<AddBankCardContract.Presenter> implements AddBankCardContract.View {
    @BindView(R.id.topbar_addbankcard)
    NormalTopBar mTopbarAddbankcard;
    @BindView(R.id.et_addbank_name)
    EditText mEtAddbankName;
    @BindView(R.id.et_addbank_identity)
    EditText mEtAddbankIdentity;
    @BindView(R.id.tv_addbank_bankname)
    TextView mTvAddbankBankname;
    @BindView(R.id.et_addbank_cardNo)
    EditText mEtAddbankCardNo;
    @BindView(R.id.et_addbank_number)
    EditText mEtAddbankNumber;

    @Override
    protected AddBankCardContract.Presenter onLoadPresenter() {
        return new AddBankCardPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_addbankcard);
    }

    @Override
    public void init() {
        super.init();

    }

    @OnClick({R.id.linear_addbank_bankname, R.id.btn_addbank_next,R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_addbank_bankname:
                chooseBank();
                break;
            case R.id.btn_addbank_next:
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }

    /**
     * 选择银行卡
     */
    private void chooseBank() {
        final View view = View.inflate(getmActivity(), R.layout.pp_chose_bank, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_ppchosebank);
        recyclerView.setLayoutManager(new LinearLayoutManager(getmActivity()));
        recyclerView.setAdapter(new CommonAdapter<String>(getmActivity(), R.layout.item_recycleview_chose_bank, BankData.getBankList()) {

            @Override
            public void convert(ViewHolder viewHolder, final String s) {
                if (!TextUtils.isEmpty(s)) {
                    viewHolder.setText(R.id.tv_chose_bank, s);
                    viewHolder.setImageResource(R.id.iv_chose_bank, BankData.getBank(s));
                }

                viewHolder.setOnClickListener(R.id.linear_chose_bank, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTvAddbankBankname.setText(s);
                        getPresenter().setBank(s);
                        closePopupWindow();
                    }
                });
            }
        });
        setUpPopupWindow(view);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        UiUtils.backgroundAlpha(1.0f,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != BankData.bankMap) {
            BankData.bankMap.clear();
            BankData.bankMap = null;
        }
        if (null != BankData.bankList) {
            BankData.bankList.clear();
            BankData.bankList = null;
        }
    }
}
