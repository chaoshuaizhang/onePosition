package cn.shopin.oneposition.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.customview.MyListView;

/**
 * Created by zcs on 2016/12/5.
 */
public class PcOnlineFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyListView listView;
    private List<String> dataList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frag_pconline, null);
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {
            parentView.removeView(view);
            return view;
        }
        listView = (MyListView) view.findViewById(R.id.listview);
        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("---> " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }
}
