package cn.shopin.oneposition.fragments.moviefrag.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;

/**
 * Created by zcs on 2016/12/11.
 */
public class CollectionFrag extends Fragment {
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CollectionFrag", "---onCreate---");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_collection, null);
        listView = (ListView) view.findViewById(R.id.listview);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            list.add("--> " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        Log.d("CollectionFrag", "---onCreateView---");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("CollectionFrag", "---onActivityCreated---");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("CollectionFrag", "---onStart---");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("CollectionFrag", "---onResume---");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("CollectionFrag", "---onPause---");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("CollectionFrag", "---onStop---");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("CollectionFrag", "---onDestroyView---");
    }
}
