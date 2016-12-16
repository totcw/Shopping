package com.betterda.shopping.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.betterda.shopping.R;

/**
 * 搜索框
 * Created by Administrator on 2016/12/16.
 */

public class SearchView extends FrameLayout {
    private ImageView mIvDelete;
    private EditText mEtSearch;
    private TextView mTvCancel;

    private onTextChangeListener listener;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.layout_search, null);
        addView(inflate);
        mIvDelete = (ImageView) inflate.findViewById(R.id.iv_layout_search_delete);
        mEtSearch = (EditText) inflate.findViewById(R.id.et_search);
        mTvCancel = (TextView) inflate.findViewById(R.id.tv__search_cancel);

        mIvDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtSearch.setText("");
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null && charSequence.length() > 0) {
                    mIvDelete.setVisibility(VISIBLE);
                    if (listener != null) {
                        listener.load(charSequence.toString());
                    }
                } else {
                    mIvDelete.setVisibility(GONE);
                    if (listener != null) {

                        listener.cancel();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void setTvCancelClickListener(OnClickListener listener) {
        mTvCancel.setOnClickListener(listener);
    }


    public void setListener(onTextChangeListener listener) {
        this.listener = listener;
    }

    public interface onTextChangeListener {
        void load(String content);

        void cancel();
    }

    public void setTvCancelVisable(boolean visable) {
        mTvCancel.setVisibility(visable?VISIBLE:GONE);
    }

    public void setEtText(String s) {
        mEtSearch.setText(s);
    }
}
