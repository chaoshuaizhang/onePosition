package cn.shopin.oneposition.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.customview.PieCharts;
import cn.shopin.oneposition.customview.slideactivity.Slidr;
import cn.shopin.oneposition.customview.slideactivity.SlidrConfig;
import cn.shopin.oneposition.customview.slideactivity.SlidrListener;
import cn.shopin.oneposition.customview.slideactivity.SlidrPosition;
import okhttp3.Call;
import okhttp3.Response;

public class AAAActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private List<ImageView> imgs;
    private PieCharts pieCharts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aaa);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pieCharts = (PieCharts) findViewById(R.id.pie);
        pieCharts.setPieFT(null);
        imgs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ImageView img = new ImageView(this);
            img.setImageResource(R.mipmap.ic_launcher);
            imgs.add(img);
        }
        pagerAdapter = new MyPagerAdapter(this, imgs);
        viewPager.setAdapter(pagerAdapter);
        SlidrConfig config = new SlidrConfig.Builder()
                .position(SlidrPosition.LEFT)
                .scrimStartAlpha(0f)
                .scrimEndAlpha(0f)
                .velocityThreshold(2400)
                .distanceThreshold(0.25f)
                .distanceThreshold(0.5f)
                .edge(true)
                .build();
        Slidr.attach(this, config);
    }

    public void btnClick(View view) {
        Toast.makeText(this, "AAAAAAAAAA", Toast.LENGTH_SHORT).show();
    }
}

class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ImageView> imgs;

    public MyPagerAdapter(Context mContext, List<ImageView> imgs) {
        this.mContext = mContext;
        this.imgs = imgs;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方推荐写法
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgs.get(position));//添加页卡
        return imgs.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position));//删除页卡
    }
}
