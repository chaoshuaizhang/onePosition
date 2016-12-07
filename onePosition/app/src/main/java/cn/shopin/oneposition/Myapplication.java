package cn.shopin.oneposition;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by zcs on 2016/12/7.
 */
public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary();
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            Log.d("Application","Application---");
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        Log.d("Application","Application+++");
        LeakCanary.install(this);
    }
}
