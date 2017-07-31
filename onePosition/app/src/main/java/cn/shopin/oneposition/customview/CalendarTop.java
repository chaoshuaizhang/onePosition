package cn.shopin.oneposition.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.util.DeviceUtil;

/**
 * Created by zcs on 2017/6/16.
 */
public class CalendarTop extends View {
    private Context mContext;
    //上横线颜色
    private int mTopLineColor = 0;
    //下横线颜色
    private int mBottomLineColor = 0;
    //周一到周五的颜色
    private int mWeekdayColor = 0;
    //周六、周日的颜色
    private int mWeekendColor = 0;
    //文字
    private String[] mWeekStrs = new String[]{"日", "一", "二", "三", "四", "五", "六"};
    //文字大小
    private float mTextSize = 15;
    //画笔
    private Paint mPaint = new Paint();
    //横线高
    private float mStrokeHeight;
    private DisplayMetrics mDisplayMetrics;
    private TypedArray mTypedArray;

    public CalendarTop(Context context) {
        this(context, null);
    }

    public CalendarTop(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mTypedArray = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.CalendarTopView, 0, 0);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mTopLineColor = mTypedArray.getColor(R.styleable.CalendarTopView_lineColor, mContext.getResources().getColor(R.color.colorAccent));
            mBottomLineColor = mTypedArray.getColor(R.styleable.CalendarTopView_lineColor, mContext.getResources().getColor(R.color.colorAccent));
            mWeekendColor = mTypedArray.getColor(R.styleable.CalendarTopView_weekEndColor, mContext.getResources().getColor(R.color.colorBlue));
            mWeekdayColor = mTypedArray.getColor(R.styleable.CalendarTopView_weekDayColor, mContext.getResources().getColor(R.color.colorQQDel));
            mTextSize = mTypedArray.getDimension(R.styleable.CalendarTopView_weekTextSize, DeviceUtil.dip2px(mContext, mTextSize));
        } else {
            mTopLineColor = mTypedArray.getColor(R.styleable.CalendarTopView_lineColor, mContext.getResources().getColor(R.color.colorAccent, mContext.getTheme()));
            mBottomLineColor = mTypedArray.getColor(R.styleable.CalendarTopView_lineColor, mContext.getResources().getColor(R.color.colorAccent, mContext.getTheme()));
            mWeekendColor = mTypedArray.getColor(R.styleable.CalendarTopView_weekEndColor, mContext.getResources().getColor(R.color.colorBlue, mContext.getTheme()));
            mWeekdayColor = mTypedArray.getColor(R.styleable.CalendarTopView_weekDayColor, mContext.getResources().getColor(R.color.colorQQDel, mContext.getTheme()));
            mTextSize = mTypedArray.getDimension(R.styleable.CalendarTopView_weekTextSize, DeviceUtil.dip2px(mContext, mTextSize));
        }
        mTypedArray.recycle();
        mStrokeHeight = DeviceUtil.dip2px(mContext, 2);
        mDisplayMetrics = getResources().getDisplayMetrics();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            //20px 转换为 dp
            heightSize = (int) (mDisplayMetrics.density * (mTextSize + getPaddingTop() + getPaddingBottom()));
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = DeviceUtil.getWidth(mContext);
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Style.FILL：实心
        //Style.STROKE：空心
        int width = getWidth();
        int height = getHeight();
        //画上横线
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeHeight);
        mPaint.setColor(mTopLineColor);
        canvas.drawLine(getPaddingLeft(), getY(), width - getPaddingLeft() - getPaddingRight(), getY(), mPaint);
        int textSpaceWidth = width / 7;//每个文字所占的空间
        mPaint.setTextSize(mTextSize);
        mPaint.setStyle(Paint.Style.FILL);
        //画文字
        for (int i = 0, length = mWeekStrs.length; i < length; i++) {
            float textWidth = mPaint.measureText(mWeekStrs[i]);
            float padLeft = (textSpaceWidth - textWidth) / 2;
            //文字是放在 ascent和descent之间的，所以让accent和descent居中即可
            float padTop = height / 2 - (mPaint.ascent() + mPaint.descent()) / 2;
            if (mWeekStrs[i].equals("日") || mWeekStrs[i].equals("六")) {
                mPaint.setColor(mWeekendColor);
            } else {
                mPaint.setColor(mWeekdayColor);
            }
            canvas.drawText(mWeekStrs[i], padLeft + textSpaceWidth * i, padTop, mPaint);
        }
        //画下横线
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeHeight);
        mPaint.setColor(mBottomLineColor);
        canvas.drawLine(getPaddingLeft(), height, width - getPaddingLeft() - getPaddingRight(), height, mPaint);
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
        invalidate();
    }
}
