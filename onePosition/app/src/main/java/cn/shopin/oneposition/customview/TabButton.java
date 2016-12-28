package cn.shopin.oneposition.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import cn.shopin.oneposition.R;

/**
 * Created by zcs on 2016/12/12.
 * <p/>
 * Error:Execution failed for task ':app:processDebugResources'.
 * > com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: Process 'command 'D:\23.0.0sdk\23.0.0sdk\build-tools\24.0.0\aapt.exe'' finished with non-zero exit value 1
 */
public class TabButton extends LinearLayout {
    private String[] tabsStr;

    public TabButton(Context context) {
        super(context);
    }

    public TabButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabButton);
        String content = typedArray.getString(R.styleable.TabButton_tab_content);
        tabsStr = content.split(",");
        typedArray.recycle();
    }

    public TabButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private int childCounts = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int childWidthMeasureSpec = 0;
        int maxWidth = 0;
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
        childCounts = getChildCount();
        if (childCounts == 0) {
            return;
        } else {
            maxWidth = widthSize / childCounts;
            childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, widthMode);//设置子view的模式
        }
        for (int i = 0; i < childCounts; i++) {
            ItemView child = (ItemView) getChildAt(i);
            measureChild(child, childWidthMeasureSpec, heightMeasureSpec);
            maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            child.setWidth(maxWidth);
            child.setHeight(maxHeight);
            if (i == 0) {
                child.setBackgroundResource(R.drawable.bt_left);
            } else if (i == childCounts) {
                child.setBackgroundResource(R.drawable.bt_right);
            } else {
                child.setBackgroundResource(R.drawable.bt_center);
            }
        }
        setMeasuredDimension(widthSize, maxHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//调用此方法是重新计算
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!changed) return;
        int count = getChildCount();
        int left = 0;
        for (int i = 0; i < count; i++) {
            ItemView child = (ItemView) getChildAt(i);
            child.setText(tabsStr[i]);
            child.layout(left, 0, left + child.getMeasuredWidth(), getHeight());
            child.setGravity(Gravity.CENTER);
            left += child.getMeasuredWidth();
        }
    }
}

/**
 * switch (widthMode) {
 * case MeasureSpec.EXACTLY://根据设定值
 * width = widthSize;
 * height = heightSize;
 * break;
 * case MeasureSpec.AT_MOST://父组件能够给出的最大的空间
 * measure(widthMeasureSpec, heightMeasureSpec);
 * break;
 * case MeasureSpec.UNSPECIFIED:
 * break;
 * }
 */