package cn.shopin.oneposition.fragments.moviefrag.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.MySwipeDelAdapter;
import cn.shopin.oneposition.customview.PieCharts;
import cn.shopin.oneposition.customview.PieFT;
import cn.shopin.oneposition.entity.movie.CollectEntity;
import cn.shopin.oneposition.util.db.DBManager;

/**
 * Created by zcs on 2016/12/11.
 */
public class CollectionFrag extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private MySwipeDelAdapter mAdapter;
    private List<CollectEntity> dataList;
    private DBManager dbManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
        dbManager = new DBManager(getActivity());
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
            return view;
        }
        mAdapter = new MySwipeDelAdapter(getActivity(), dataList);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(mAdapter);
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        // TODO: 2017/3/24 读取数据库
//        dataList.addAll(dbManager.query());
//        if (dataList.size() == 0) {
//            Toast.makeText(getActivity(), "为空", Toast.LENGTH_SHORT).show();
//        } else {
//            Log.d("dbManager", new Gson().toJson(dataList));
//            Toast.makeText(getActivity(), "不为空", Toast.LENGTH_SHORT).show();
//        }
        CollectEntity coll = new CollectEntity();
        coll.setSummary("jjjjjjjjjjjj");
        dataList.add(coll);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
