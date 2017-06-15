package cn.shopin.oneposition.fragments.moviefrag.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.MySwipeDelAdapter2;
import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.fragments.BaseMvpFragment;
import cn.shopin.oneposition.fragments.webdetail.MovieDetailActivity;
import cn.shopin.oneposition.model.db.DBManager;

/**
 * Created by zcs on 2016/12/11.
 */
public class CollectionFrag extends BaseMvpFragment<CollectionPresenter> implements MySwipeDelAdapter2.MyItemClickListener, CollectionContract.ICollectionView {
    @BindView(R.id.listView)
    protected ListView listView;
    private MySwipeDelAdapter2 mAdapter;
    private List<CollectEntity> dataList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
    }

    /*
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (view == null) {
                view = inflater.inflate(R.layout.frag_collection, null);
            }
            ViewGroup parentView = (ViewGroup) view.getParent();
            if (null != parentView) {
                parentView.removeView(view);
                return view;
            }
            mAdapter = new MySwipeDelAdapter2(getActivity(), dataList, this);
            listView = (ListView) view.findViewById(R.id.recyclerview);
            listView.setAdapter(mAdapter);
            initData();
            return view;
        }
    */
    @Override
    public void initView() {
        mAdapter = new MySwipeDelAdapter2(getActivity(), dataList, this);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        // TODO: 2017/3/24 读取数据库
        mPresenter.getDataFromDb();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void clickListener(int position) {
        Toast.makeText(getActivity(), "-----点击-----" + position, Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("entity", dataList.get(position));
        startActivity(new Intent(getActivity(), MovieDetailActivity.class).putExtra("bundle", bundle));
    }

    @Override
    public void longClickListener(int position) {
        Toast.makeText(getActivity(), "长按    点击-----" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteItem(int id, int position) {
        mPresenter.deleteItemFromDb(id);
        dataList.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("fraglife", "collection  is  destroy");
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_collection;
    }

    @Override
    protected void initEventAndData() {
        initView();
        initListener();
        initData();
    }

    @Override
    public void getDataFromDb(List<CollectEntity> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }
}
