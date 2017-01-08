package cn.shopin.oneposition.customview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/26.
 */
public class ItemView extends TextView {
    private int bgColor;
    private boolean isChecked;
    private Fragment fragment;

    public ItemView(Context context) {
        super(context);
        setClickable(true);
        setTextColor(Color.BLACK);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
