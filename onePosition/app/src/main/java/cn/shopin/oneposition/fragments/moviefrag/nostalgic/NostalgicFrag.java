package cn.shopin.oneposition.fragments.moviefrag.nostalgic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.RecyclerAdapter;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.fragments.webdetail.MovieDetailActivity;
import cn.shopin.oneposition.util.DateUtil;
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
    private List<NostalgicEntity> dataList;
    private int firstVisiblePosition = 0;
    private int lastVisiblePosition = 0;
    private String timeStr = "0";
    private boolean loadMore = false;

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
            loadMore = true;
            view = inflater.inflate(R.layout.frag_nostalgic, null);
        } else {
            loadMore = false;
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {
            parentView.removeView(view);
            return view;
        }
        initView();
        initData();
        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.addOnScrollListener(new RecyclerOnScrollListener());
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        // TODO: 2017/2/10 解决recyclerview滑动卡顿
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerAdapter = new RecyclerAdapter(this.getActivity(), dataList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClick(new RecyclerAdapter.setclickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == dataList.size()) {//加载更多
                    View view = recyclerView.getChildAt(position);
                    timeStr = DateUtil.getSubDate(dataList.get(position - 1).getCreatetime());
                    loadMore = true;
                    recyclerAdapter.setLaoding(loadMore);
                    initData();
                } else {//点击跳转到webview
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("entity", dataList.get(position));
                    startActivity(new Intent(getActivity(), MovieDetailActivity.class).putExtra("bundle", bundle));
                }
            }
        });
    }

    private void init() {
        dataList = new ArrayList<>();
    }

    private void initData() {
        if (!loadMore) {
            return;
        }
        MovieApi movieApi = RetrofitUtil.createService(MovieApi.class, Cans.TAG_MOVIE);
        movieApi.getMovieNostalgic(timeStr, "10", "2", "0", "39")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NostalgicEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<NostalgicEntity> moviePieceEntities) {
                        dataList.addAll(moviePieceEntities);
                        recyclerAdapter.notifyDataSetChanged();
                        loadMore = false;
                        initData();
                        recyclerAdapter.setLaoding(loadMore);
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
            }
            lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition();
            if (lastVisiblePosition == dataList.size() - 1) {
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }
    }
}
