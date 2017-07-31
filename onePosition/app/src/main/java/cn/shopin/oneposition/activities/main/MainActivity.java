package cn.shopin.oneposition.activities.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.shopin.oneposition.fragments.BalloonFragment;
import cn.shopin.oneposition.fragments.heartfrag.HeartFragment;
import cn.shopin.oneposition.fragments.LvRadioFragment;
import cn.shopin.oneposition.fragments.PcOnlineFragment;
import cn.shopin.oneposition.fragments.moviefrag.MovieFragment;
import cn.shopin.oneposition.util.ToastUtil;

/**
 *
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.IMainView {

    private SparseArray<Fragment> frags = new SparseArray<>();
    private SparseArray<TextView> textViews = new SparseArray<>();
    private String[] iconsText;
    private SlidingMenu slidingMenu;
    private NavigationView navigationView;
    private FragmentTransaction mTransaction;
    private MovieFragment movieFragment;
    private HeartFragment heartFragment;
    private LvRadioFragment radioFragment;
    private PcOnlineFragment pcOnlineFragment;
    private BalloonFragment balloonFragment;
    @BindView(R.id.textMovie)
    protected TextView textMovie;
    @BindView(R.id.textHeart)
    protected TextView textHeart;
    @BindView(R.id.textRadio)
    protected TextView textRadio;
    @BindView(R.id.textOcean)
    protected TextView textOcean;
    @BindView(R.id.textBalloon)
    protected TextView textBalloon;
    private int tag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        initView();
        initData();
    }

    public void initView() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMenu(R.layout.slip_menu);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置滑动边缘
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffset(200);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    }

    public void initListener() {
    }

    @Override
    public void initData() {
        mTransaction = getSupportFragmentManager().beginTransaction();
        initFrags();
        setDefaultFrag(tag);
    }

    @Override
    public void initFrags() {
        movieFragment = new MovieFragment();
        heartFragment = new HeartFragment();
        radioFragment = new LvRadioFragment();
        pcOnlineFragment = new PcOnlineFragment();
        balloonFragment = new BalloonFragment();
        frags.put(0, movieFragment);
        frags.put(1, heartFragment);
        frags.put(2, radioFragment);
        frags.put(3, pcOnlineFragment);
        frags.put(4, balloonFragment);
        textViews.put(0, textMovie);
        textViews.put(1, textHeart);
        textViews.put(2, textRadio);
        textViews.put(3, textOcean);
        textViews.put(4, textBalloon);
        for (int i = 0; i < frags.size(); i++) {
            mTransaction.add(R.id.container, frags.get(i));
        }
    }

    @Override
    public void setDefaultFrag(int tag) {
        switchFrag(tag);
    }

    @OnClick({R.id.textMovie, R.id.textHeart, R.id.textRadio, R.id.textOcean, R.id.textBalloon})
    public void click(View view) {
        int tag = Integer.valueOf(view.getTag().toString());
        if (tag != this.tag) {
            resetSelected();
            switchFrag(tag);
        }
    }

    @Override
    public void switchFrag(int tag) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        if (tag == -1) {//第一次进入时，没有需要隐藏的Frag
            tag = 0;
            mTransaction.add(R.id.container, frags.get(tag)).commit();
        } else {
            if (frags.get(tag).isAdded()) {
                mTransaction.hide(frags.get(this.tag)).show(frags.get(tag)).commit();
            } else {
                mTransaction.hide(frags.get(this.tag)).add(R.id.container, frags.get(tag)).commit();
            }
        }
        textViews.get(tag).setSelected(true);
        this.tag = tag;
    }

    private void resetSelected() {
        textMovie.setSelected(false);
        textHeart.setSelected(false);
        textRadio.setSelected(false);
        textOcean.setSelected(false);
        textBalloon.setSelected(false);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

}
