package cn.shopin.oneposition.fragments.heartfrag;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.ScaleInTransformer;
import cn.shopin.oneposition.adapter.ViewPagerAdapter;
import cn.shopin.oneposition.customview.CircleImageView;

/**
 * Created by zcs on 2016/12/5.
 */
public class HeartFragment extends Fragment {
    private CircleImageView circleImageView;
    private List<ImageView> imgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "HeartFragment onCreateView");
        View view = inflater.inflate(R.layout.frag_heart, null);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.circle_imageview);
        Glide.with(getActivity()).load("http://img3.duitang.com/uploads/item/201408/25/20140825082917_LaGy4.thumb.224_0.jpeg").asBitmap().into(circleImageView);
        imgs = new ArrayList<>();
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getActivity(), imgs);
        viewPager.setAdapter(pagerAdapter);
        return view;
    }



}
