package com.betterda.shopping.factory;

import com.betterda.shopping.interfac.GlideImage;
import com.betterda.shopping.interfac.LoadImageInterface;

/**
 * 返回加载图片的框架的工厂方法
 * Created by Administrator on 2016/12/26.
 */

public class LoadImageFactory {
        //创建加载图片的对象
        public static LoadImageInterface getLoadImageInterface(){
            return new GlideImage();
        }
}
