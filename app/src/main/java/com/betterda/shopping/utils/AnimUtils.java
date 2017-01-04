package com.betterda.shopping.utils;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.betterda.shopping.R;

import static com.betterda.shopping.R.id.view;

/**
 * Created by Administrator on 2017/1/4.
 */

public class AnimUtils {
    /**
     *
     * @param activity
     * @param add //加号的view
     * @param anim_mask_layout  根布局
     * @param imgCart  购物车的view
     */
    public static void playAnimation(Activity activity ,View add,ViewGroup anim_mask_layout,View imgCart){

        int[] des = new int[2];
        add.getLocationInWindow(des);
        ImageView img = new ImageView(activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
        layoutParams.height = UtilMethod.dip2px(activity,15);
        layoutParams.width = UtilMethod.dip2px(activity,15);
        img.setLayoutParams(layoutParams);
        img.setImageResource(R.drawable.shape_oval);
        setAnim(anim_mask_layout,imgCart,img,des);
    }

    /**
     *

     * @param anim_mask_layout 当前界面的根布局
     * @param imgCart 购物车的view
     * @param v 动画view 红色的圆
     * @param start_location //起始位置
     */

    private static  void setAnim(final ViewGroup anim_mask_layout, View imgCart, final View v, int[] start_location) {

        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(imgCart,start_location[0],start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                //直接remove可能会因为界面仍在绘制中成而报错
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                    }
                },100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }


    /**
     *    //创建动画 平移动画直接传递偏移量
     * @param imgCart 是购物车的view
     * @param startX
     * @param startY
     * @return
     */
    private  static Animation createAnim(View imgCart ,int startX, int startY){
        int[] des = new int[2];
        imgCart.getLocationInWindow(des);

        AnimationSet set = new AnimationSet(false);

        Animation translationX = new TranslateAnimation(0, des[0]-startX, 0, 0);
        //线性插值器 默认就是线性
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1]-startY);
        //设置加速插值器
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1,0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setFillAfter(true);
        set.setDuration(500);

        return set;
    }

    /**
     * 计算动画view在根部局中的坐标 添加到根部局中
     * vg 是根布局
     * view 是动画view
     */

    private static  void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {

        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y-loc[1]);
        vg.addView(view);
    }


}
