package com.betterda.shopping.ziti;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.base.IPresenter;
import com.betterda.shopping.utils.ImageTools;


/**
 * 二维码activity
 * Created by Administrator on 2016/6/27.
 */
public class EweiMaActivity extends BaseActivity {
    private ImageView iv;
    private Bitmap bitmap;

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_eweima);
        iv = (ImageView) findViewById(R.id.iv_eweima);
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    public void init() {
        super.init();
        String bianhao = getIntent().getStringExtra("bianhao");
        String orderId = getIntent().getStringExtra("orderId");
        if (TextUtils.isEmpty(bianhao)) {
            bitmap = ImageTools.generateQRCode("1",getmActivity());
        } else {
            bitmap = ImageTools.generateQRCode(bianhao+","+orderId,getmActivity());
        }

        iv.setImageBitmap(bitmap);
    }
}
