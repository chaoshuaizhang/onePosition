package cn.shopin.oneposition.fragments.moviefrag.collection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.MySwipeDelAdapter;
import cn.shopin.oneposition.adapter.MySwipeDelAdapter2;
import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.fragments.webdetail.MovieDetailActivity;
import cn.shopin.oneposition.util.db.DBManager;

/**
 * Created by zcs on 2016/12/11.
 */
public class CollectionFrag extends Fragment implements MySwipeDelAdapter2.MyItemClickListener {
    private View view;
    private ListView listView;
    private MySwipeDelAdapter2 mAdapter;
    private List<CollectEntity> dataList;
    private DBManager dbManager;
    private ItemClickListener itemClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
        dbManager = new DBManager(getActivity());
        Log.d("fraglife", "CollectionFrag-----onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frag_collection, null);
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (null != parentView) {
            parentView.removeView(view);
            Log.d("fraglife", "null != parentView");
            return view;
        } else {
            Log.d("fraglife", "null == parentView");
        }
        Log.d("fraglife", "CollectionFrag-----onCreateView");
        mAdapter = new MySwipeDelAdapter2(getActivity(), dataList, this);
        listView = (ListView) view.findViewById(R.id.recyclerview);
        itemClickListener = new ItemClickListener();
        listView.setAdapter(mAdapter);
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        // TODO: 2017/3/24 读取数据库
        dataList.clear();
        dataList.addAll(dbManager.query());
        if (dataList.size() == 0) {
            Toast.makeText(getActivity(), "数据库为空", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("dbManager", new Gson().toJson(dataList));
            Toast.makeText(getActivity(), "数据库不为空", Toast.LENGTH_SHORT).show();
        }
        mAdapter.notifyDataSetChanged();
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

    class ItemClickListener implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "onItemClick", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "onItemLongClick", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("fraglife", "collection  is  destroy");
    }
}
