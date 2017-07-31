package cn.shopin.oneposition.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcs on 2017/6/17.
 */

public class CalendarViewPager extends ViewPager {
    private Map<Integer, CalendarContentView> mContentViews = new HashMap<>();

    public CalendarViewPager(Context context) {
        this(context, null);
    }

    public CalendarViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
