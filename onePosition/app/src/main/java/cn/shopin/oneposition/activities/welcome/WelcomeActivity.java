package cn.shopin.oneposition.activities.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.shopin.oneposition.activities.LoginActivity;
import cn.shopin.oneposition.activities.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.welcome_img)
    protected ImageView welcomeTmg;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        setWelcomeImg();
        handler.sendEmptyMessageDelayed(0, 2000);
    }

/*    @Override
    protected WelcomeContract.IWelcomePresenter createPresenter(WelcomeContract.IWelcomeView view) {
        return null;
    }*/

    private void setWelcomeImg() {

    }

    /**
     * 设置全屏
     */
    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏虚拟键
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
