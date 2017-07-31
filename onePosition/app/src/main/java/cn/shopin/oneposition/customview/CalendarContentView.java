package cn.shopin.oneposition.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by zcs on 2017/6/17.
 * 日历每页的内容
 */

public class CalendarContentView extends ViewGroup {
    public CalendarContentView(Context context) {
        this(context, null);
    }

    public CalendarContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
