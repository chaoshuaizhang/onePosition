package cn.shopin.oneposition.activities.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.shopin.oneposition.fragments.BalloonFragment;
import cn.shopin.oneposition.fragments.HeartFragment;
import cn.shopin.oneposition.fragments.LvRadioFragment;
import cn.shopin.oneposition.fragments.PcOnlineFragment;
import cn.shopin.oneposition.fragments.moviefrag.MovieFragment;

/**
 * 电影 我心 直播 太平洋 氢气球
 */
public class MainActivity extends BaseMvpActivity<MainContract.IMainView, MainPresenter> implements MainContract.IMainView {

    private FragmentTabHost fragTabHost;
    private Class[] fragClass;
    private int[] icons;
    private String[] iconsText;
    private ImageView centerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    @Override
    protected MainPresenter createPresenter(MainContract.IMainView view) {
        return new MainPresenter(this);
    }

    private void initData() {
        fragClass = new Class[]{MovieFragment.class, HeartFragment.class, LvRadioFragment.class,
                PcOnlineFragment.class, BalloonFragment.class};
        icons = new int[]{R.drawable.icon_movie_pressed, R.drawable.icon_heart_pressed, R.drawable.icon_radio_pressed,
                R.drawable.icon_ocean_pressed, R.drawable.icon_balloon_pressed};
        iconsText = new String[]{"movie", "heart", "", "ocean", "balloon"};

    }

    private void initView() {
        centerImg = (ImageView) findViewById(R.id.center_img);
        fragTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//        slidingMenu = new SlidingMenu(this);
//        slidingMenu.setMode(SlidingMenu.LEFT);
//        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置滑动边缘
//        // 设置滑动菜单视图的宽度
//        slidingMenu.setBehindOffsetRes(200);
//        // 设置渐入渐出效果的值
//        slidingMenu.setFadeDegree(0.35f);
//        /**
//         * SLIDING_WINDOW will include the Title/ActionBar in the content
//         * section of the SlidingMenu, while SLIDING_CONTENT does not.
//         */
//        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        fragTabHost.getTabWidget().setDividerDrawable(null);
//        fragTabHost.getTabWidget().setAnimation(new AnimationSet());
        for (int i = 0; i < icons.length; i++) {
            TabHost.TabSpec tabSpec = fragTabHost.newTabSpec("Tab" + i).setIndicator(getTabsView(i));
            fragTabHost.addTab(tabSpec, fragClass[i], null);
        }
        mPresenter.fun_main_presenter();
    }

    private void initListener() {
    }

    private View getTabsView(int i) {
        //取得布局实例
        View view = View.inflate(MainActivity.this, R.layout.frag_item_indicator, null);
        //取得布局对象
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_img);
        TextView textView = (TextView) view.findViewById(R.id.tab_tv);
        //设置图标
        imageView.setBackgroundResource(icons[i]);
        textView.setText(iconsText[i]);
        return view;
    }

    @Override
    public void fun_main_view() {
        Log.d("TAG", "fun_main_view");
    }
}
