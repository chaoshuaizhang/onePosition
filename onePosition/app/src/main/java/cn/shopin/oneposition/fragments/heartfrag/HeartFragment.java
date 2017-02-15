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
import cn.shopin.oneposition.fragments.BaseMvpFragment;
import cn.shopin.oneposition.fragments.heartfrag.HeartContract.IHeartModel;

/**
 * Created by zcs on 2016/12/5.
 */
public class HeartFragment extends BaseMvpFragment<HeartContract.IHeartView, HeartContract.IHeartPresenter> implements HeartContract.IHeartView {
    private View view;
    private CircleImageView circleImageView;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private List<ImageView> imgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frag_heart, null);
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {//说明已经加载过了
            parentView.removeView(view);
            return view;
        }
        initView();
        return view;
    }


    @Override
    public void initView() {
        imgs = new ArrayList<>();
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        pagerAdapter = new ViewPagerAdapter(getActivity(), imgs);
        viewPager.setAdapter(pagerAdapter);
        circleImageView = (CircleImageView) view.findViewById(R.id.circle_imageview);
        Glide.with(getActivity()).load("http://img3.duitang.com/uploads/item/201408/25/20140825082917_LaGy4.thumb.224_0.jpeg").asBitmap().into(circleImageView);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
    }


    @Override
    protected HeartContract.IHeartPresenter createPresenter(HeartContract.IHeartView view) {
        return new HeartPresenter(this);
    }
}
