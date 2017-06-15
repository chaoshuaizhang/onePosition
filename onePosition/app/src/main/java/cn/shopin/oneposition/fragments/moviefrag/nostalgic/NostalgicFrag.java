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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.RecyclerAdapter;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.fragments.BaseMvpFragment;
import cn.shopin.oneposition.fragments.webdetail.MovieDetailActivity;
import cn.shopin.oneposition.util.DateUtil;
import cn.shopin.oneposition.util.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zcs on 2016/12/11.
 */
public class NostalgicFrag extends BaseMvpFragment<NostalgicPresenter> implements NostalgicContract.INostalgicView {
    private View view;
    @BindView(R.id.recyclerview)
    protected RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private List<NostalgicEntity> dataList;
    private int firstVisiblePosition = 0;
    private int lastVisiblePosition = 0;
    private String timeStr = "0";
    private boolean loadMore = false;//避免重复执行onCreateViewV时，重富加载数据

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
        initInject();
        return view;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_nostalgic;
    }

    @Override
    protected void initEventAndData() {
        initView();
        initData();
    }

    public void initView() {
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
                    loadData(timeStr);
                } else {//点击跳转到webview
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("entity", dataList.get(position));
                    startActivity(new Intent(getActivity(), MovieDetailActivity.class).putExtra("bundle", bundle));
                }
            }
        });
    }

    @Override
    public void initListener() {

    }

    private void init() {
        dataList = new ArrayList<>();
    }

    public void initData() {
        if (loadMore) {
            loadData(timeStr);
        }
    }

    @Override
    public void loadData(String timeStr) {
        mPresenter.getMovieNostalgic(timeStr, "10", "2", "0", "39");
    }

    @Override
    public void getMovieNostalgic(List<NostalgicEntity> datas) {
        dataList.addAll(datas);
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.setLaoding(false);
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
