package cn.shopin.oneposition.fragments.webdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.shopin.oneposition.R;

/**
 * Created by zcs on 2017/3/20.
 */

public class TransparentFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.transparent_layout, container, false);
    }
}
