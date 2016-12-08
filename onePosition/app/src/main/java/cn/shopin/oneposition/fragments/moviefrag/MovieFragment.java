package cn.shopin.oneposition.fragments.moviefrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.fragments.BaseMvpFragment;

/**
 * Created by zcs on 2016/12/5.
 */
public class MovieFragment extends BaseMvpFragment<MovieContract.IMovieView, MoviePresenter> implements MovieContract.IMovieView {
    /**
     * fragment初始化的时候调用，我们通常在这个方法中使
     * 用getArguments获取activity传来的初始化fragment的参数。
     * 注意：这个方法中我们不能获取activity中的控件！
     * 因为，activity的onCreate还没有执行完，即activity还没有创建完，
     * 要想获取activity相关的资源应该在onActivityCreated中获取。
     * 从activity和fragment的生命周期对比图可以明显看出这一点。
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return view;
    }
}
