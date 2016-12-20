package cn.shopin.oneposition.fragments.moviefrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.ViewPagerAdapter;
import cn.shopin.oneposition.fragments.BaseMvpFragment;
import cn.shopin.oneposition.fragments.moviefrag.collection.CollectionFrag;
import cn.shopin.oneposition.fragments.moviefrag.nostalgic.NostalgicFrag;
import cn.shopin.oneposition.fragments.moviefrag.piecerate.MoviePieceFrag;

/**
 * Created by zcs on 2016/12/5.
 *
 * @desc fragmentTabHost只会加载第一个
 */
public class MovieFragment extends BaseMvpFragment<MovieContract.IMovieView, MoviePresenter> implements MovieContract.IMovieView {
    private FrameLayout fragContainer;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private List<ImageView> imgs;
    private SparseArray<Fragment> sparseArray;
    private CollectionFrag collectionFrag;
    private NostalgicFrag nostalgicFrag;
    private MoviePieceFrag moviePieceFrag;

    /**
     * fragment初始化的时候调用，我们通常在onCreate方法中使
     * 用getArguments获取activity传来的初始化fragment的参数。
     * 注意：这个方法中我们不能获取activity中的控件！
     * 因为，activity的onCreate还没有执行完，即activity还没有创建完，
     * 要想获取activity相关的资源应该在onActivityCreated中获取。
     * 从activity和fragment的生命周期对比图可以明显看出这一点。
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "MovieFragment onCreate");
    }

    @Override
    protected MoviePresenter createPresenter(MovieContract.IMovieView view) {
        return new MoviePresenter(this);
    }

    /**
     * 这个方法中我们主要是通过布局填充器获取fragment布局.
     * Nullable 表示可以为空-即：可传空值
     */
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "MovieFragment onCreateView");
        View view = inflater.inflate(R.layout.frag_movie, null);
        fragContainer = (FrameLayout) view.findViewById(R.id.frag_container);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        sparseArray = new SparseArray<>();
        inidFrags();
        initViewPager();
        initData();
        return view;
    }

    /**
     * 初始化Frags
     */
    private void inidFrags() {
        collectionFrag = new CollectionFrag();
        nostalgicFrag = new NostalgicFrag();
        moviePieceFrag = new MoviePieceFrag();
        sparseArray.put(1, collectionFrag);
        sparseArray.put(2, nostalgicFrag);
        sparseArray.put(3, moviePieceFrag);
    }

    private void initViewPager() {
        imgs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(getActivity());
            imgs.add(img);
        }
        pagerAdapter = new ViewPagerAdapter(getActivity(), imgs);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "MovieFragment onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "MovieFragment onResume");
    }

    public void initView() {

    }

    public void initListener() {

    }

    //进行数据加载
    public void initData() {
        mPresenter.getBanners();
    }
}
