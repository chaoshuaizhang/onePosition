package cn.shopin.oneposition.fragments.moviefrag.nostalgic;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.RecyclerAdapter;
import cn.shopin.oneposition.adapter.RecyclerAdapter1;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.customview.MyListView;
import cn.shopin.oneposition.entity.movie.MoviePieceEntity;
import cn.shopin.oneposition.util.RetrofitUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zcs on 2016/12/11.
 */
public class NostalgicFrag extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private List<MoviePieceEntity> dataList;
    private int firstVisiblePosition = 0;
    private int lastVisiblePosition = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frag_nostalgic, null);
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {
            parentView.removeView(view);
            return view;
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.addOnScrollListener(new RecyclerOnScrollListener());
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // TODO: 2017/2/10 解决recyclerview滑动卡顿 
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnScrollListener(new RecyclerOnScrollListener());
        initData();
        return view;
    }

    private void init() {
        dataList = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(this.getActivity(), dataList);
    }

    private void initData() {
        MovieApi movieApi = RetrofitUtil.createService(MovieApi.class, Cans.TAG_MOVIE);
        movieApi.getMoviePiece("0", "10", "2", "0", "39")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MoviePieceEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<MoviePieceEntity> moviePieceEntities) {
                        dataList.clear();
                        dataList.addAll(moviePieceEntities);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });
    }

    class RecyclerOnScrollListener extends OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            firstVisiblePosition = manager.findFirstCompletelyVisibleItemPosition();
            if (firstVisiblePosition == 0) {
                Log.d("TTTASD", "滑到顶部了");
            }
            lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition();
            if (lastVisiblePosition == dataList.size() - 1) {
                Log.d("TTTASD", "滑到底部了");
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, 200);
        }
    }
}
