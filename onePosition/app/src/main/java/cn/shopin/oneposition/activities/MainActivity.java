package cn.shopin.oneposition.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.fragments.BalloonFragment;
import cn.shopin.oneposition.fragments.HeartFragment;
import cn.shopin.oneposition.fragments.LvRadioFragment;
import cn.shopin.oneposition.fragments.MovieFragment;
import cn.shopin.oneposition.fragments.PcOnlineFragment;

/**
 * 电影 我心 直播 太平洋 氢气球
 */
public class MainActivity extends AppCompatActivity {

    private FragmentTabHost fragTabHost;
    private Class[] fragClass;
    private int[] bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        fragClass = new Class[]{MovieFragment.class, HeartFragment.class, LvRadioFragment.class,
                PcOnlineFragment.class, BalloonFragment.class};
        bg = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    }

    private void initView() {
        fragTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        for (int i = 0; i < bg.length; i++) {
            TabHost.TabSpec tabSpec = fragTabHost.newTabSpec("Tab" + i).setIndicator(new View(this));
            fragTabHost.addTab(tabSpec, fragClass[i], null);
        }
    }

    private void initListener() {
    }

    private View getTabsView(int i) {
        //取得布局实例
        View view = View.inflate(MainActivity.this, R.layout.frag_item_indicator, null);
        //取得布局对象
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        //设置图标
        imageView.setImageResource(bg[i]);
        return view;
    }
}
