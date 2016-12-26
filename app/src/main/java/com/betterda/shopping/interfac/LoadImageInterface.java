package com.betterda.shopping.interfac;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

/**
 * 加载图片的接口
 * Created by Administrator on 2016/12/26.
 */

public interface LoadImageInterface {
    //加载图片
   void loadImageCrop(Activity activity,String url, ImageView view);
   void loadImageFit(Activity activity,String url, ImageView view);
    void loadImageCropRound(Activity activity,String url, ImageView view);
   void loadImageFitRound(Activity activity,String url, ImageView view);
    void loadImageCropCircle(Activity activity,String url, ImageView view);
   void loadImageFitCircle(Activity activity,String url, ImageView view);
}
