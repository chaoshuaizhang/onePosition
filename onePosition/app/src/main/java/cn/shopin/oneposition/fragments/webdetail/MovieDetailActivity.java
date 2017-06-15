package cn.shopin.oneposition.fragments.webdetail;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.shopin.oneposition.adapter.FragPagerAdapter;
import cn.shopin.oneposition.customview.MyViewPager;
import cn.shopin.oneposition.customview.slideactivity.Slidr;
import cn.shopin.oneposition.customview.slideactivity.SlidrConfig;
import cn.shopin.oneposition.customview.slideactivity.SlidrPosition;

/**
 * 电影详情页
 * 底部上滑消失-下滑显示
 */
public class MovieDetailActivity extends BaseMvpActivity<MovieDetailPresenter> implements ViewPager.OnPageChangeListener, MovieDetailContract.IMovieDetailView {
    @BindView(R.id.viewpager)
    protected MyViewPager viewPager;
    private FragPagerAdapter fragPagerAdapter;
    private List<Fragment> frags;
    private TransparentFrag transparentFrag;
    private WebDetailFrag webDetailFrag;
    private WebCommentFrag webCommentFrag;
    private SlidrConfig slidrConfig;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initEventAndData() {
        initView();
        initData();
    }

    @Override
    public void initView() {
        frags = new ArrayList<>();
        fragPagerAdapter = new FragPagerAdapter(getSupportFragmentManager(), frags);
        viewPager.setAdapter(fragPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setFadingEdgeLength(50);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        transparentFrag = new TransparentFrag();
        webDetailFrag = new WebDetailFrag();
        webCommentFrag = new WebCommentFrag();
        webDetailFrag.setArguments(getIntent().getBundleExtra("bundle"));
        frags.add(transparentFrag);
        frags.add(webDetailFrag);
        frags.add(webCommentFrag);
        fragPagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(1, false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            finish();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}

/*
    private void initSlidrConfig() {
        slidrConfig = new SlidrConfig.Builder()
                .position(SlidrPosition.LEFT)
                .scrimStartAlpha(0f)
                .scrimEndAlpha(0f)
                .velocityThreshold(2400)
                .distanceThreshold(0.25f)
                .distanceThreshold(0.5f)
                .edge(true)
                .build();
        Slidr.attach(this, slidrConfig);
    }
*/
