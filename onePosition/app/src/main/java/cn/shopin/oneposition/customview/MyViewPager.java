package cn.shopin.oneposition.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by zcs on 2017/3/20.
 * 功能：触摸屏幕非边缘部分不滑动，触碰边缘 滑动
 */

public class MyViewPager extends ViewPager {
    private Context context;
    private GestureDetector gestureDetector = null;
    private int width = 0;
    private int SCREEN_EDGE = 30;

    public MyViewPager(Context context) {
        super(context);
        this.context = context;
        this.gestureDetector = new GestureDetector(context, new MyGestureDetector());
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.gestureDetector = new GestureDetector(context, new MyGestureDetector());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
    }

    /*
    * @return true:自己消费  false:不消费
    * 如果 return true，事件会分发给当前 View 并由 dispatchTouchEvent 方法进行消费，同时事件会停止向下传递；
    * 如果 return false，事件分发分为两种情况：
    * 如果当前 View 获取的事件直接来自 Activity，则会将事件返回给 Activity 的 onTouchEvent 进行消费；
    * 如果当前 View 获取的事件来自外层父控件，则会将事件返回给父 View 的  onTouchEvent 进行消费。
    * 如果返回系统默认的 super.dispatchTouchEvent(ev)，事件会自动的分发给当前 View 的 onInterceptTouchEvent 方法。
    * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /*
    * @return true:自己消费   false:不消费
    * 如果 onInterceptTouchEvent 返回 true，则表示将事件进行拦截，并将拦截到的事件交由当前 View 的 onTouchEvent 进行处理；
    * 如果 onInterceptTouchEvent 返回 false，则表示将事件放行，当前 View 上的事件会被传递到子 View 上，再由子 View 的 dispatchTouchEvent 来开始这个事件的分发；
    * 如果 onInterceptTouchEvent 返回 super.onInterceptTouchEvent(ev)，事件默认会被拦截，并将拦截到的事件交由当前 View 的 onTouchEvent 进行处理。
    * */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("SimpleOnGestureListener", "" + ev.getX() + "   " + width);
                if (ev.getX() > SCREEN_EDGE && ev.getX() < width - SCREEN_EDGE) {//不可以滑动
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /*
    * @return true:自己消费   false:不消费
    * 如果事件传递到当前 View 的 onTouchEvent 方法，而该方法返回了 false，那么这个事件会从当前 View 向上传递，并且都是由上层 View 的 onTouchEvent 来接收，如果传递到上面的 onTouchEvent 也返回 false，这个事件就会“消失”，而且接收不到下一次事件。
    * 如果返回了 true 则会接收并消费该事件。
    * 如果返回 super.onTouchEvent(ev) 默认处理事件的逻辑和返回 false 时相同。
    * */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    /**
     * 手势控制类
     * 单击、双击 等等。
     */
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}

