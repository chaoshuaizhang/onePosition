package cn.shopin.oneposition.customview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zcs on 2017/3/24.
 */

public class SwipeDelView extends FrameLayout {
    private ViewDragHelper viewDragHelper;
    private View contentView, deleteView;
    private int contentViewWidth;
    private int deleteViewHeight;
    private int deleteViewWidth;
    private int lastX;
    private int lastY;
    private SwipeStatus mSwipeStatus = SwipeStatus.Close;
    private OnSwipeStatusChangeListener onSwipeStatusChangeListener;

    enum SwipeStatus {
        Open, Close, Swiping
    }

    public SwipeDelView(Context context) {
        this(context, null);
    }

    public SwipeDelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeDelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        deleteView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        contentViewWidth = contentView.getMeasuredWidth();
        deleteViewHeight = deleteView.getMeasuredHeight();
        deleteViewWidth = deleteView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        contentView.layout(0, 0, contentViewWidth, deleteViewHeight);
        deleteView.layout(contentViewWidth, 0, contentViewWidth + deleteViewWidth, deleteViewHeight);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                int deltxY = y - lastY;
                if (Math.abs(deltaX) > Math.abs(deltxY)) {
                    //认为想滑动删除，拦截该事件，不让ListView去处理
                    requestDisallowInterceptTouchEvent(true);//子View不想让他的父View拦截
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return contentView == child || deleteView == child;
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return deleteViewWidth;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentView) {
                if (left > 0) left = 0;//右边不能滑动，左边可以滑动
                if (left < -deleteViewWidth) left = -deleteViewWidth;//向左滑动有限制
            } else {
                if (left < (contentViewWidth - deleteViewWidth))
                    left = contentViewWidth - deleteViewWidth;
                if (left > contentViewWidth) left = contentViewWidth;
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            Log.e("SwipeView", "left:" + left + " top:" + top + "  dx:" + dx + "  dy:" + dy);
            Log.e("SwipeView", "deleteView.getLeft()：" + deleteView.getLeft() + "   deleteView.getRight():" + deleteView.getRight());
            if (changedView == contentView) {
                deleteView.layout(deleteView.getLeft() + dx, 0, deleteView.getRight() + dx, deleteView.getBottom());
            } else {
                contentView.layout(contentView.getLeft() + dx, 0, contentView.getRight() + dx, contentView.getBottom());
            }
            //设置状态
            if (contentView.getLeft() == 0 && mSwipeStatus != SwipeStatus.Close) {
                mSwipeStatus = SwipeStatus.Close;
                if (onSwipeStatusChangeListener != null) {
                    onSwipeStatusChangeListener.onClose(SwipeDelView.this);
                }
            } else if (contentView.getLeft() == -deleteViewWidth && mSwipeStatus != SwipeStatus.Open) {
                mSwipeStatus = SwipeStatus.Open;
                onSwipeStatusChangeListener.onOpen(SwipeDelView.this);
            } else if (mSwipeStatus != SwipeStatus.Swiping) {
                mSwipeStatus = SwipeStatus.Swiping;
                onSwipeStatusChangeListener.onSwiping(SwipeDelView.this);
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (contentView.getLeft() < -deleteViewWidth / 2) {
                //open
                //由于其内部封装了Scroller
                open();
            } else {
                //close
                close();
            }
        }
    };

    public void close() {
        viewDragHelper.smoothSlideViewTo(contentView, 0, 0);
        ViewCompat.postInvalidateOnAnimation(this);//刷新
    }

    public void fastClose() {
        contentView.layout(0, 0, contentViewWidth, deleteViewHeight);
        deleteView.layout(contentViewWidth, 0, contentViewWidth + deleteViewWidth, deleteViewHeight);
        //手动更新  不会调用onViewPositionChanged
        mSwipeStatus = SwipeStatus.Close;
        if (onSwipeStatusChangeListener != null) {
            onSwipeStatusChangeListener.onClose(SwipeDelView.this);
        }
    }

    public void open() {
        viewDragHelper.smoothSlideViewTo(contentView, -deleteViewWidth, 0);
        ViewCompat.postInvalidateOnAnimation(this);//刷新
    }


    /**
     * viewDragHelper封装了Sroller，获取和移动由Sroller完成，我们只需要负责刷新即可
     */
    @Override
    public void computeScroll() {
        //continueSettling返回true表明动画没有结束。
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(SwipeDelView.this);//刷新
        }
    }

    public SwipeStatus getSwipeStatus() {
        return mSwipeStatus;
    }

    public void setSwipeStatus(SwipeStatus mSwipeStatus) {
        this.mSwipeStatus = mSwipeStatus;
    }

    public OnSwipeStatusChangeListener getOnSwipeStatusChangeListener() {
        return onSwipeStatusChangeListener;
    }

    public void setOnSwipeStatusChangeListener(OnSwipeStatusChangeListener onSwipeStatusChangeListener) {
        this.onSwipeStatusChangeListener = onSwipeStatusChangeListener;
    }

    /**
     * 将滑动状态暴漏给外界
     */
    public interface OnSwipeStatusChangeListener {
        void onOpen(SwipeDelView openedSwipeView);

        void onClose(SwipeDelView closedSwipeView);

        void onSwiping(SwipeDelView swipingSwipeView);
    }

}
