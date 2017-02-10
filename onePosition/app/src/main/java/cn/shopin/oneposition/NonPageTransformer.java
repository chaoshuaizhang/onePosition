package cn.shopin.oneposition;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zcs on 2017/1/22.
 */

public class NonPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        page.setScaleX(1.0f);//hack
    }

    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();
}