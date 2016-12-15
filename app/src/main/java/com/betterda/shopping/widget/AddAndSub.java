package com.betterda.shopping.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterda.shopping.R;

/**
 * 加和减少的view
 * Created by Administrator on 2016/12/13.
 */

public class AddAndSub extends FrameLayout {
    private ImageView mAdd,mSub;
    private TextView mTextView;

    private AddAndSubClickInterface mAddAndSubClickInterface;


    public AddAndSub(Context context) {
        this(context, null);
    }

    public AddAndSub(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        View inflate = View.inflate(context, R.layout.layout_add, null);
        mAdd = (ImageView) inflate.findViewById(R.id.iv_layout_add);
        mSub = (ImageView) inflate.findViewById(R.id.iv_layout_delete);
        mTextView = (TextView) inflate.findViewById(R.id.tv_layout_amount);
        mAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                add(1);
                if (mAddAndSubClickInterface != null) {
                    mAddAndSubClickInterface.add(1);
                }
            }
        });

        mSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                sub(1);
                if (mAddAndSubClickInterface != null) {
                    mAddAndSubClickInterface.sub(1);
                }
            }
        });
        addView(inflate);
    }

    /**
     * 加
     * @param amount
     */
    public void add(int amount) {
        String trim = mTextView.getText().toString().trim();
        try {
            int s = Integer.parseInt(trim)+ amount;
            mTextView.setText(s+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 减
     * @param amount
     */
    public void sub(int amount) {
        String trim = mTextView.getText().toString().trim();
        try {
            int s = Integer.parseInt(trim)-amount;
            if (s < 1) {
                s = 1;
            }
            mTextView.setText(s+"");
        } catch (Exception e) {

        }
    }

    public String getAmount() {
        return mTextView.getText().toString().trim();
    }

    public void setAmount(String amount) {
        mTextView.setText(amount);
    }


    public void setmAddAndSubClickInterface(AddAndSubClickInterface mAddAndSubClickInterface) {
        this.mAddAndSubClickInterface = mAddAndSubClickInterface;
    }


    public interface AddAndSubClickInterface{

        void add(int amount);

        void sub(int amount);
    }
}
