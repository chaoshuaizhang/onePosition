package cn.shopin.oneposition.fragments.moviefrag.piecerate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.shopin.oneposition.R;

/**
 * Created by zcs on 2016/12/11.
 */
public class MoviePieceFrag extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CollectionFrag", "---onCreate---222");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_moviepiece, null);
        return view;
    }
}
