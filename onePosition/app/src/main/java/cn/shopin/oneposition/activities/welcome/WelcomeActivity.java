package cn.shopin.oneposition.activities.welcome;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.shopin.oneposition.activities.main.MainActivity;
import cn.shopin.oneposition.customview.CalendarTop;
import cn.shopin.oneposition.entity.movie.WelcomeEntity;

public class WelcomeActivity extends BaseMvpActivity<WelcomePresenter> implements WelcomeContract.IWelcomeView {
    @BindView(R.id.welcome_img)
    protected ImageView welcomeTmg;
    private List<WelcomeEntity.GroupsBean> groups = new ArrayList<>();
    private String imgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497343470139&di=f348b5f0c92f0df7366c868d17bf98a3&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201410%2F01%2F20141001135859_Ei2Cn.jpeg";
    private CalendarTop calendarTop;

    /**
     * 此处重写的目的：为了在setContentView执行前设置是否全屏
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFullScreen = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.setWelcomeImg();
        calendarTop = (CalendarTop) findViewById(R.id.calendarTop);
    }

    @Override
    public void setImgs(WelcomeEntity welcomeEntity) {
        groups.addAll(welcomeEntity.getGroups());
        //Picasso.with(this).load(groups.get(0).getImgUrls().get(0)).into(welcomeTmg);
        Picasso.with(this).load(imgPath).fit().into(welcomeTmg);
//        mPresenter.actionToMainActivity();
    }

    @Override
    public void jumpToMain() {
        MainActivity.start(this);
        finish();
    }

    public void click(View view) {
        DisplayMetrics mDisplayMetrics = getResources().getDisplayMetrics();
        Toast.makeText(this, "densityDpi:" + mDisplayMetrics.densityDpi + "   density:" + mDisplayMetrics.density, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "heightPixels:" + mDisplayMetrics.heightPixels + "   widthPixels:" + mDisplayMetrics.widthPixels, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "scaledDensity:" + mDisplayMetrics.scaledDensity, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "xdpi:" + mDisplayMetrics.xdpi + "  " + "ydpi:" + mDisplayMetrics.ydpi, Toast.LENGTH_LONG).show();
        calendarTop.setTextSize(39);
    }

}
