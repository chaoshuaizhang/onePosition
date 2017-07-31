package cn.shopin.oneposition.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zcs on 2017/6/16.
 * 定义线条
 */

public class Line extends View {
    private Context mContext;
    private Paint mLinePaint;

    public Line(Context context) {
        this(context, null);
    }

    public Line(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Line(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        setMeasuredDimension(width, heightSize);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mLinePaint = new Paint();
        canvas.drawLine(getX(), getY(), getX() + getWidth(), getY(), mLinePaint);
    }
}
