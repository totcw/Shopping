package com.betterda.shopping.interfac;

import android.app.Activity;
import android.widget.ImageView;

import com.betterda.shopping.utils.GlideCircleTransform;
import com.betterda.shopping.utils.GlideRoundTransform;
import com.bumptech.glide.Glide;

/**
 * 使用gilde加载图片
 * Created by Administrator on 2016/12/26.
 */

public class GlideImage implements  LoadImageInterface {

    /**
     * 加载图片 拉伸到控件大小
     * @param activity
     * @param url
     * @param view
     */
    @Override
    public void loadImageCrop(Activity activity, String url, ImageView view) {
        Glide.with(activity).load(url).centerCrop().into(view);
    }

    /**
     * 加载图片 不拉伸
     * @param activity
     * @param url
     * @param view
     */
    @Override
    public void loadImageFit(Activity activity, String url, ImageView view) {
        Glide.with(activity).load(url).fitCenter().into(view);
    }

    /**
     * 加载圆角图片, 拉伸
     * @param activity
     * @param url
     * @param view
     */
    @Override
    public void loadImageCropRound(Activity activity, String url, ImageView view) {
        Glide.with(activity).load(url).centerCrop().transform(new GlideRoundTransform(activity)).into(view);
    }

    /**
     * 加载圆角图片,不拉伸
     * @param activity
     * @param url
     * @param view
     */
    @Override
    public void loadImageFitRound(Activity activity, String url, ImageView view) {
        Glide.with(activity).load(url).fitCenter().transform(new GlideRoundTransform(activity)).into(view);
    }

    /**
     * 加载圆形图片,拉伸
     * @param activity
     * @param url
     * @param view
     */
    @Override
    public void loadImageCropCircle(Activity activity, String url, ImageView view) {
        Glide.with(activity).load(url).centerCrop().transform(new GlideCircleTransform(activity)).into(view);
    }

    /**
     * 加载圆形图片,不拉伸
     * @param activity
     * @param url
     */
    @Override
    public void loadImageFitCircle(Activity activity, String url, ImageView view) {
        Glide.with(activity).load(url).fitCenter().transform(new GlideRoundTransform(activity)).into(view);
    }
}
