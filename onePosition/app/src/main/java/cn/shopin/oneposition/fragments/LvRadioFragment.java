package cn.shopin.oneposition.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.customview.CircleProgressView;

/**
 * Created by zcs on 2016/12/5.
 */
public class LvRadioFragment extends Fragment {
    private CircleProgressView progressView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "LvRadioFragment onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "LvRadioFragment onActivityCreated");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "LvRadioFragment onCreateView");
        View view = inflater.inflate(R.layout.frag_lvradio, null);
        progressView = (CircleProgressView) view.findViewById(R.id.circle_progressview);
        progressView.setProgress(66);
        return view;
    }
}
