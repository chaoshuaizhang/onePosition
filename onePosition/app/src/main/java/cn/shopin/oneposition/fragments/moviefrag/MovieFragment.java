package cn.shopin.oneposition.fragments.moviefrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.ViewPagerAdapter;
import cn.shopin.oneposition.customview.ItemView;
import cn.shopin.oneposition.customview.TabButton;
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
    private TabButton tabButton;
    private FragmentManager fragManager;
    private View selectedTab;

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
        fragManager = getActivity().getSupportFragmentManager();
        inidFrags();
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
        View view = inflater.inflate(R.layout.frag_movie, null);
        /*if (view == null) {
            view = inflater.inflate(R.layout.frag_movie, null);
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {
            parentView.removeView(view);
            return view;
        }*/
        fragContainer = (FrameLayout) view.findViewById(R.id.frag_container);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabButton = (TabButton) view.findViewById(R.id.bt_hovertab);
        //定义hover item
        final ItemView item1 = new ItemView(getActivity());
        item1.setTag("left");
        final ItemView item2 = new ItemView(getActivity());
        item2.setTag("center");
        final ItemView item3 = new ItemView(getActivity());
        item3.setTag("right");
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setSelected(true);
                item2.setSelected(false);
                item3.setSelected(false);
                if (sparseArray.get(1).isAdded()) {
                    fragManager.beginTransaction().hide(sparseArray.get(4)).show(sparseArray.get(1)).commit();
                } else {
                    fragManager.beginTransaction().hide(sparseArray.get(4)).add(R.id.frag_container, sparseArray.get(1)).commit();
                }
                sparseArray.put(4, sparseArray.get(1));
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setSelected(false);
                item2.setSelected(true);
                item3.setSelected(false);
                if (sparseArray.get(2).isAdded()) {
                    fragManager.beginTransaction().show(sparseArray.get(2)).commit();
                } else {
                    fragManager.beginTransaction().hide(sparseArray.get(4)).add(R.id.frag_container, sparseArray.get(2)).commit();
                }
                sparseArray.put(4, sparseArray.get(2));
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setSelected(false);
                item2.setSelected(false);
                item3.setSelected(true);
                if (sparseArray.get(3).isAdded()) {
                    fragManager.beginTransaction().hide(sparseArray.get(4)).show(sparseArray.get(3)).commit();
                } else {
                    fragManager.beginTransaction().hide(sparseArray.get(4)).add(R.id.frag_container, sparseArray.get(3)).commit();
                }
                sparseArray.put(4, sparseArray.get(3));
                Toast.makeText(getActivity(), fragManager.getFragments().size() + " " + fragContainer.getChildCount(), Toast.LENGTH_SHORT).show();
            }
        });
        if (tabButton.getChildCount() == 0) {
            tabButton.addView(item1);
            tabButton.addView(item2);
            tabButton.addView(item3);
        }
        initViewPager();
        initData();
        if (sparseArray.get(4).isAdded()) {
            fragManager.beginTransaction().hide(sparseArray.get(4)).show(sparseArray.get(4)).commit();
        } else {
            fragManager.beginTransaction().add(R.id.frag_container, sparseArray.get(4)).commit();
        }
        return view;
    }

    /**
     * 初始化Frags
     */
    private void inidFrags() {
        collectionFrag = new CollectionFrag();
        nostalgicFrag = new NostalgicFrag();
        moviePieceFrag = new MoviePieceFrag();
        sparseArray = new SparseArray<>();
        sparseArray.put(1, collectionFrag);
        sparseArray.put(2, nostalgicFrag);
        sparseArray.put(3, moviePieceFrag);
        sparseArray.put(4, collectionFrag);
    }

    private void initViewPager() {
        imgs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(getActivity());
            img.setImageResource(R.mipmap.vp);
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
//        mPresenter.getBanners();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
