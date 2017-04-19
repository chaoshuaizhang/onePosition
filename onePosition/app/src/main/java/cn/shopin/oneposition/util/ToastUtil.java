package cn.shopin.oneposition.util;

import android.content.Context;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import cn.shopin.oneposition.R;

/**
 * Created by zcs on 2017/4/7.
 */

public class ToastUtil {
    public static void showToast(Context mContext, String msg) {
        View toastView = LayoutInflater.from(mContext).inflate(R.layout.layout_toast_msg, null);
        TextView toastMsg = (TextView) toastView.findViewById(R.id.toast_msg);
        toastMsg.setText(msg);
        Toast toast = new Toast(mContext);
        //获取屏幕高度  
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题  
        toast.setGravity(Gravity.TOP, 0, displayMetrics.heightPixels / 2);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.show();
    }
}
