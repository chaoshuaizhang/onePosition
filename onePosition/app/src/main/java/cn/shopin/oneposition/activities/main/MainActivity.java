package cn.shopin.oneposition.activities.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
<<<<<<< HEAD:onePosition/app/src/main/java/cn/shopin/oneposition/activities/MainActivity.java
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
=======
import android.util.Log;
>>>>>>> d04866da69d4bea101010182596450b4c75df838:onePosition/app/src/main/java/cn/shopin/oneposition/activities/main/MainActivity.java
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.shopin.oneposition.fragments.BalloonFragment;
import cn.shopin.oneposition.fragments.HeartFragment;
import cn.shopin.oneposition.fragments.LvRadioFragment;
import cn.shopin.oneposition.fragments.moviefrag.MovieFragment;
import cn.shopin.oneposition.fragments.PcOnlineFragment;

/**
 * movie heart lv  balloon
 */
public class MainActivity extends BaseMvpActivity<MainContract.IMainView, MainPresenter> implements MainContract.IMainView {

    private FragmentTabHost fragTabHost;
    private Class[] fragClass;
    private int[] icons;

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

    }

    private void initView() {
        fragTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
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
        //设置图标
        imageView.setBackgroundResource(icons[i]);
        return view;
    }

    @Override
    public void fun_main_view() {
        Log.d("TAG", "fun_main_view");
    }
}
