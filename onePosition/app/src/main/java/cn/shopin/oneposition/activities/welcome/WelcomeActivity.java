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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.shopin.oneposition.activities.LoginActivity;
import cn.shopin.oneposition.activities.main.MainActivity;
import cn.shopin.oneposition.entity.movie.WelcomeEntity;

public class WelcomeActivity extends BaseMvpActivity<WelcomePresenter> implements WelcomeContract.IWelcomeView {
    @BindView(R.id.welcome_img)
    protected ImageView welcomeTmg;
    private List<WelcomeEntity.GroupsBean> groups = new ArrayList<>();
    private String imgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497343470139&di=f348b5f0c92f0df7366c868d17bf98a3&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201410%2F01%2F20141001135859_Ei2Cn.jpeg";

    /**
     * 此处重写的目的：为了在setContentView前设置是否全屏
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
    }

    @Override
    public void setImgs(WelcomeEntity welcomeEntity) {
        groups.addAll(welcomeEntity.getGroups());
        //Picasso.with(this).load(groups.get(0).getImgUrls().get(0)).into(welcomeTmg);
        Picasso.with(this).load(imgPath).fit().into(welcomeTmg);
        mPresenter.actionToMainActivity();
    }

    @Override
    public void jumpToMain() {
        MainActivity.start(this);
        finish();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
