package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * Created by zcs on 2017/3/20.
 * 上拉和下拉不显示 进度par和文字
 * 默认为空白
 */

public class MyRotateLoadingLayout extends LoadingLayout {

    public MyRotateLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        mHeaderImage.setVisibility(GONE);
        mHeaderProgress.setVisibility(GONE);
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {
        hideAllViews();
    }

    protected void onPullImpl(float scaleOfLayout) {
        hideAllViews();
    }

    @Override
    protected void refreshingImpl() {
        hideAllViews();
    }

    @Override
    protected void resetImpl() {
        hideAllViews();
    }

    private void resetImageRotation() {
        hideAllViews();
    }

    @Override
    protected void pullToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected void releaseToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.default_ptr_rotate;
    }

}