package cn.shopin.oneposition.fragments.heartfrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.ScaleInTransformer;
import cn.shopin.oneposition.adapter.ViewPagerAdapter;

/**
 * Created by zcs on 2016/12/5.
 */
public class HeartFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "HeartFragment onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "HeartFragment onActivityCreated");
    }

    private List<ImageView> imgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "HeartFragment onCreateView");
        View view = inflater.inflate(R.layout.frag_heart, null);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        imgs = new ArrayList<>();
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getActivity(), imgs);
        viewPager.setAdapter(pagerAdapter);
        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(getActivity());
            img.setImageResource(R.mipmap.vp);
            imgs.add(img);
        }
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(true, new ScaleInTransformer());
        pagerAdapter.notifyDataSetChanged();
        return view;
    }

}
