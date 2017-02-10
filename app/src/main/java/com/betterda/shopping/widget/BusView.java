package com.betterda.shopping.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.shopping.R;
import com.betterda.shopping.bus.BusActivity;
import com.betterda.shopping.login.LoginActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.utils.UtilMethod;

/**
 * 购物车控件
 * Created by Administrator on 2016/12/16.
 */

public class BusView extends FrameLayout{
    private RelativeLayout mRelativeBus;
    private TextView mTvBus;

    public BusView(Context context) {
        this(context, null);
    }

    public BusView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.layout_bus, null);
        mRelativeBus = (RelativeLayout) inflate.findViewById(R.id.relative_layout_bus);
        mTvBus = (TextView) inflate.findViewById(R.id.tv_layout_bus);
        addView(inflate);

        mRelativeBus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilMethod.isLogin(context, LoginActivity.class);
            }
        });
    }


    public void setTvBusVisable(boolean visable) {
        mTvBus.setVisibility(visable?VISIBLE:INVISIBLE);
    }

    public void setTvBusText(String amount) {
        mTvBus.setText(amount);
    }

    public String getBusText() {
        return mTvBus.getText().toString().trim();
    }

}
