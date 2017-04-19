package cn.shopin.oneposition.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by zcs on 2017/4/5.
 */

public class WeekDayView extends View {
    //上横线颜色
    private int mTopLineColor = Color.parseColor("#FF4081");
    //下横线颜色
    private int mBottomLineColor = Color.parseColor("#FF4081");
    //周一到周五的颜色
    private int mWeedayColor = Color.parseColor("#1FC2F3");
    //周六、周日的颜色
    private int mWeekendColor = Color.parseColor("#fa4451");
    //线的宽度
    private int mStrokeWidth = 4;
    private int mWeekSize = 14;
    private Paint paint;
    private DisplayMetrics mDisplayMetrics;
    private String[] weekString = new String[]{"日", "一", "二", "三", "四", "五", "六"};

    public WeekDayView(Context context) {
        this(context, null);
    }

    public WeekDayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekDayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        mDisplayMetrics = getResources().getDisplayMetrics();
    }

    /*
    getMeasuredWidth:在自定义view重写onLayout时、在我们用layoutinflater动态加载view后想获得view的原始宽度时。
    getWidth:一般在view已经布局后呈现出来了，想获取宽度时
    */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = mDisplayMetrics.densityDpi * 20;
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = mDisplayMetrics.densityDpi * 200;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        //进行画上下线
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mTopLineColor);
        paint.setStrokeWidth(mStrokeWidth);
        canvas.drawLine(0, 0, width, 0, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(mWeekSize * mDisplayMetrics.scaledDensity);
        //画下横线
        paint.setColor(mBottomLineColor);
        canvas.drawLine(0, height, width, height, paint);
        int columnWidth = width / 7;
        for (int i = 0; i < 7; i++) {
            String text = weekString[i];
            int fontWidth = (int) paint.measureText(text);
            int startX = columnWidth * i + (columnWidth - fontWidth) / 2;
            int startY = (int) (height / 2 - (paint.ascent() + paint.descent()) / 2);
            if (text.indexOf("日") > -1 || text.indexOf("六") > -1) {
                paint.setColor(mWeekendColor);
            } else {
                paint.setColor(mWeedayColor);
            }
            canvas.drawText(text, startX, startY, paint);
        }
    }

    /**
     * 设置顶线的颜色
     *
     * @param mTopLineColor
     */
    public void setmTopLineColor(int mTopLineColor) {
        this.mTopLineColor = mTopLineColor;
    }

    /**
     * 设置底线的颜色
     *
     * @param mBottomLineColor
     */
    public void setmBottomLineColor(int mBottomLineColor) {
        this.mBottomLineColor = mBottomLineColor;
    }

    /**
     * 设置周一-五的颜色
     *
     * @return
     */
    public void setmWeedayColor(int mWeedayColor) {
        this.mWeedayColor = mWeedayColor;
    }

    /**
     * 设置周六、周日的颜色
     *
     * @param mWeekendColor
     */
    public void setmWeekendColor(int mWeekendColor) {
        this.mWeekendColor = mWeekendColor;
    }

    /**
     * 设置边线的宽度
     *
     * @param mStrokeWidth
     */
    public void setmStrokeWidth(int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
    }


    /**
     * 设置字体的大小
     *
     * @param mWeekSize
     */
    public void setmWeekSize(int mWeekSize) {
        this.mWeekSize = mWeekSize;
    }


    /**
     * 设置星期的形式
     *
     * @param weekString 默认值  "日","一","二","三","四","五","六"
     */
    public void setWeekString(String[] weekString) {
        this.weekString = weekString;
    }
}
