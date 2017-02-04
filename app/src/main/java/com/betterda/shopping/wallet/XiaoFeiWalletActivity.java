package com.betterda.shopping.wallet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.betterda.shopping.R;
import com.betterda.shopping.utils.UiUtils;

import butterknife.OnClick;

/**
 * 消费钱包
 * Created by Administrator on 2016/12/20.
 */

public class XiaoFeiWalletActivity extends CashWalletActivity {

    @Override
    public void init() {
        super.init();
        mTopbarCashwallet.setTitle("消费钱包");
        mRelativeWallet2Chongzhi.setVisibility(View.GONE);
    }

    @OnClick({ R.id.bar_action,R.id.bar_back,R.id.tv_cashwallet_shouming})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bar_action:
                Intent intent = new Intent(getmActivity(), MingXiActivity.class);
                intent.putExtra("type", "消费钱包");
                UiUtils.startIntent(getmActivity(),intent);
                break;
            case R.id.bar_back:
                back();
                break;
            case R.id.tv_cashwallet_shouming://说明
                AlertDialog.Builder builder = new AlertDialog.Builder(getmActivity());
                builder.setTitle("消费钱包说明")
                        .setMessage("消费钱包会在下单的时候自动抵用一半的金额")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        });
                builder.show();
                break;

        }

    }
}
