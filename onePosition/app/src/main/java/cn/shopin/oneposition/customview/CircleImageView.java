package cn.shopin.oneposition.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.shopin.oneposition.R;

/**
 * Created by zcs on 2017/1/23.
 *
 * @desc 定义圆角图片
 */

public class CircleImageView extends ImageView {
    //外圆的宽度
    private int outCircleWidth;
    //外圆的颜色
    private int outCircleColor = Color.WHITE;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = null;
        if (array == null) {
            array = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
            for (int i = 0; i < array.length(); i++) {
                switch (array.getIndex(i)) {
                    case R.styleable.CircleImageView_outCircleColor:
                        outCircleColor = array.getColor(array.getIndex(i), Color.WHITE);
                        break;
                    case R.styleable.CircleImageView_outCircleWidth:
//                        outCircleWidth = array
                        break;
                }
            }

        }
    }
}
