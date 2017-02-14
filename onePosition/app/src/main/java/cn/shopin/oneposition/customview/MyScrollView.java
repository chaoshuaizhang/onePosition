package cn.shopin.oneposition.customview;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;

import cn.shopin.oneposition.R;

/**
 * Created by zcs on 2016/12/11.
 */
public class MyScrollView extends ScrollView {
    private FrameLayout frameLayout;
    private OverScroller mScroller;
    private int mTopViewHeight;
    private View fragTabHost;
    private View mTop;
    private View mNav;

    public MyScrollView(Context context) {
        super(context);
        mScroller = new OverScroller(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new OverScroller(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new OverScroller(context);
    }

/*    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.viewpager);
        mNav = findViewById(R.id.bt_hovertab);
        fragTabHost = findViewById(R.id.tabhost);
        View view = findViewById(R.id.frag_container);
        if (!(view instanceof FrameLayout)) {
            throw new RuntimeException("id_stickynavlayout_viewpager show used by ViewPager !");
        }
        frameLayout = (FrameLayout) view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }
}
