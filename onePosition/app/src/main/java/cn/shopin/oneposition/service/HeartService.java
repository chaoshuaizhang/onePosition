package cn.shopin.oneposition.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.register.RegisterActivity;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class HeartService extends Service {
    public static final String ACTION = "cn.shopin.oneposition.service.HeartService";

    private Notification mNotification;
    private NotificationCompat.Builder builder;
    private NotificationManager mManager;

    public HeartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        initNotifiManager();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        new PollingThread().start();
    }

    //初始化通知栏配置
    private void initNotifiManager() {
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int icon = R.mipmap.ic_launcher;
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(icon);
        builder.setContentTitle("标题--");
        builder.setTicker("New Message");
        builder.setDefaults(Notification.DEFAULT_ALL);
    }

    //弹出Notification
    private void showNotification() {
        builder.setWhen(System.currentTimeMillis());
        //Navigator to the new activity when click the notification title
        Intent i = new Intent(this, RegisterActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);
        builder.setContentIntent(pendingIntent);
        mNotification = builder.build();
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;//点击通知后通知消失
        mManager.notify(0, mNotification);
    }

    /**
     * Polling thread
     * 模拟向Server轮询的异步线程
     */
    int count = 0;

    class PollingThread extends Thread {
        @Override
        public void run() {
            System.out.println("Polling...");
            count++;
            //当计数能被5整除时弹出通知
            if (count % 5 == 0) {
                showNotification();
                System.out.println("New message!");
            }
        }
    }
}
