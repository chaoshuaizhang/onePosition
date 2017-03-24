package cn.shopin.oneposition.fragments.heartfrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.AdverViewAdapter;
import cn.shopin.oneposition.adapter.ViewPagerAdapter;
import cn.shopin.oneposition.api.HeartApi;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.customview.AdverView;
import cn.shopin.oneposition.customview.CircleImageView;
import cn.shopin.oneposition.entity.heart.ActionLabelEntity;
import cn.shopin.oneposition.entity.heart.ConsultsResultsEntity;
import cn.shopin.oneposition.entity.heart.HomeConsults;
import cn.shopin.oneposition.entity.heart.LabelResultsEntity;
import cn.shopin.oneposition.fragments.BaseMvpFragment;
import cn.shopin.oneposition.util.RetrofitUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zcs on 2016/12/5.
 */
public class HeartFragment extends BaseMvpFragment<HeartContract.IHeartView, HeartContract.IHeartPresenter> implements HeartContract.IHeartView {
    private View view;
    private CircleImageView circleImageView;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private List<ImageView> imgs;
    private RelativeLayout callView;
    private RelativeLayout consultView;
    private RelativeLayout freeView;
    private RelativeLayout psyTestView;
    private List<ConsultsResultsEntity> dataList;
    private List<LabelResultsEntity> labelList;
    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;
    private ImageView avatar4;
    private AdverView adverView;
    private AdverViewAdapter adverViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
        labelList = new ArrayList<>();
    }

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
        initListener();
        initData();
        return view;
    }


    @Override
    public void initView() {
        imgs = new ArrayList<>();
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        callView = (RelativeLayout) view.findViewById(R.id.call);
        consultView = (RelativeLayout) view.findViewById(R.id.consult);
        freeView = (RelativeLayout) view.findViewById(R.id.free);
        psyTestView = (RelativeLayout) view.findViewById(R.id.psytest);
        adverView = (AdverView) view.findViewById(R.id.adverview);
        adverViewAdapter = new AdverViewAdapter(labelList);
        avatar1 = (ImageView) view.findViewById(R.id.counselor1_avatar);
        avatar2 = (ImageView) view.findViewById(R.id.counselor2_avatar);
        avatar3 = (ImageView) view.findViewById(R.id.counselor3_avatar);
        avatar4 = (ImageView) view.findViewById(R.id.counselor4_avatar);
        pagerAdapter = new ViewPagerAdapter(getActivity(), imgs);
        viewPager.setAdapter(pagerAdapter);
        circleImageView = (CircleImageView) view.findViewById(R.id.circle_imageview);
        Glide.with(getActivity()).load("http://img3.duitang.com/uploads/item/201408/25/20140825082917_LaGy4.thumb.224_0.jpeg").asBitmap().into(circleImageView);
    }

    @Override
    public void initListener() {
        callView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        consultView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        freeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        psyTestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        HeartApi heartApi = RetrofitUtil.createService(HeartApi.class, Cans.TAG_HEART);
        heartApi.getHomeConsults().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeConsults>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(HomeConsults homeConsults) {
                        dataList.clear();
                        dataList.addAll(homeConsults.getResults());
                        setConsultsData();
                    }
                });
        heartApi.getActionLabels().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ActionLabelEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ActionLabelEntity actionLabelEntity) {
                        labelList.clear();
                        labelList.addAll(actionLabelEntity.getResults());
                        adverView.setAdapter(adverViewAdapter);
                        adverView.start();
                    }
                });

    }

    private void setConsultsData() {
        //此处用glide加载图片显示的不清楚
        Picasso.with(getActivity()).load(dataList.get(0).getAvatar()).into(avatar1);
        Picasso.with(getActivity()).load(dataList.get(1).getAvatar()).into(avatar2);
        Picasso.with(getActivity()).load(dataList.get(2).getAvatar()).into(avatar3);
        Picasso.with(getActivity()).load(dataList.get(3).getAvatar()).into(avatar4);

    }

    @Override
    protected HeartContract.IHeartPresenter createPresenter(HeartContract.IHeartView view) {
        return new HeartPresenter(this);
    }

}
/*
*         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

* */