package cn.shopin.oneposition.fragments.heartfrag;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.view.BannerViewPager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.adapter.AdverViewAdapter;
import cn.shopin.oneposition.adapter.HeartHomeAdapter;
import cn.shopin.oneposition.adapter.ViewPagerAdapter;
import cn.shopin.oneposition.api.HeartApi;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.customview.AdverView;
import cn.shopin.oneposition.customview.BannerImageLoader;
import cn.shopin.oneposition.customview.CircleImageView;
import cn.shopin.oneposition.entity.heart.ActionLabelEntity;
import cn.shopin.oneposition.entity.heart.CarouselResultEntity;
import cn.shopin.oneposition.entity.heart.ConsultsResultsEntity;
import cn.shopin.oneposition.entity.heart.HomeCarousel;
import cn.shopin.oneposition.entity.heart.HomeConsults;
import cn.shopin.oneposition.entity.heart.LabelResultsEntity;
import cn.shopin.oneposition.fragments.BaseMvpFragment;
import cn.shopin.oneposition.util.PermissionsChecker;
import cn.shopin.oneposition.util.RetrofitUtil;
import cn.shopin.oneposition.util.ToastUtil;

/**
 * Created by zcs on 2016/12/5.
 */
public class HeartFragment extends BaseMvpFragment<HeartPresenter> implements HeartContract.IHeartView, OnBannerListener {
    @BindView(R.id.circle_imageview)
    protected CircleImageView circleImageView;
    @BindView(R.id.call)
    protected RelativeLayout callView;
    @BindView(R.id.consult)
    protected RelativeLayout consultView;
    @BindView(R.id.free)
    protected RelativeLayout freeView;
    @BindView(R.id.psytest)
    protected RelativeLayout psyTestView;
    @BindView(R.id.counselor1_avatar)
    protected ImageView avatar1;
    @BindView(R.id.counselor2_avatar)
    protected ImageView avatar2;
    @BindView(R.id.counselor3_avatar)
    protected ImageView avatar3;
    @BindView(R.id.counselor4_avatar)
    protected ImageView avatar4;
    @BindView(R.id.adverview)
    protected AdverView adverView;
    @BindView(R.id.banner)
    protected Banner banner;
    @BindView(R.id.recyclerview)
    protected RecyclerView recyclerView;
    private HeartHomeAdapter heartHomeAdapter;
    private List<String> dataList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private List<String> bannersUrl;
    private List<String> bannersTitle;
    private AdverViewAdapter adverViewAdapter;
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    private boolean isCamera = false;
    private List<ConsultsResultsEntity> consultsResultsEntities;
    private List<LabelResultsEntity> labelResultsEntities;
    //    private List<CarouselResultEntity> labelResultsEntities;
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        consultsResultsEntities = new ArrayList<>();
        labelResultsEntities = new ArrayList<>();
        bannersUrl = new ArrayList<>();
        bannersTitle = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add("00000 " + i);
        }
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_heart;
    }

    @Override
    protected void initEventAndData() {
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        adverViewAdapter = new AdverViewAdapter(labelResultsEntities);
        circleImageView.setImageBitmap(mPresenter.getBitmapByPath(mPresenter.getSavePath() + "head.jpg"));
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        heartHomeAdapter = new HeartHomeAdapter(getActivity(), dataList, R.layout.item_layout_heart_comments);
        recyclerView.setAdapter(heartHomeAdapter);
        initBanner();
//        Glide.with(getActivity()).load("http://img3.duitang.com/uploads/item/201408/25/20140825082917_LaGy4.thumb.224_0.jpeg").asBitmap().into(circleImageView);
    }

    @Override
    public void initListener() {
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.layout_change_avatar, null);
        TextView takePic = (TextView) view.findViewById(R.id.take);
        TextView selectPic = (TextView) view.findViewById(R.id.select);
        selectPic.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                isCamera = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPer(PERMISSIONS);
                } else {
                    getPicFromAlbum();
                }
                dialog.dismiss();
            }
        });
        takePic.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                isCamera = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                        requestPer(PERMISSIONS);
                    } else {
                        mPresenter.openCamera();
                    }
                } else {
                    mPresenter.openCamera();
                }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void initBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片集合
        banner.setImages(bannersUrl);
        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerListener(this);
        banner.start();
    }

    private void getPicFromAlbum() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, Cans.OPEN_ALBUM_RESULT);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // 请求权限兼容低版本
    private void requestPer(String... permissions) {
        requestPermissions(permissions, Cans.REQEST_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Cans.REQEST_PERMISSIONS: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        mPresenter.showMissingPermissionDialog();
                        return;
                    }
                }
                if (isCamera) {
                    mPresenter.openCamera();
                } else {
                    getPicFromAlbum();
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivity(intent);
    }

    @OnClick({R.id.circle_imageview, R.id.text_custom})
    public void setAvatar(View view) {
        switch (view.getId()) {
            case R.id.circle_imageview:
                showTypeDialog();
                break;
        }
    }

    @Override
    public void initData() {
        mPermissionsChecker = new PermissionsChecker(getActivity());
        mPresenter.getTopBanner("consultant");
        mPresenter.getActionLabels();
        mPresenter.getHomeConsults();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Cans.OPEN_ALBUM_RESULT:
                if (data != null && data.getData() != null)
                    mPresenter.cropPhoto(data.getData());// 裁剪图片
                break;
            case Cans.OPEN_CAMERA_RESULT:
                final File temp = new File(mPresenter.getSavePath(), "head.jpg");
                if (temp.exists()) {
                    Toast.makeText(getActivity(), "裁剪图存在" + temp.length(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "裁剪图不存在", Toast.LENGTH_LONG).show();
                }
                mPresenter.cropPhoto(null);// 裁剪图片
                break;
            case Cans.CROP_PHOTO_RESULT:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap head = extras.getParcelable("data");
                    if (head != null) {
                        // TODO: 2017/4/7 可以在此处上传服务器
                        ToastUtil.showToast(getActivity(), "裁剪图不为空");
                        mPresenter.saveBitmapoCard(head);//保存在SD卡中
                        circleImageView.setImageBitmap(head);// 用ImageView显示出来
                    } else {
                        ToastUtil.showToast(getActivity(), "裁剪图为空");
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setTopBanner(HomeCarousel homeCarousel) {
        List<String> list = new ArrayList<>();//必须这样写，不能使用bannersUrl来add,否则会被清空
        for (int i = 0, length = Integer.valueOf(homeCarousel.getResultCount()); i < length; i++) {
            list.add(homeCarousel.getResults().get(i).getPic());
        }
        banner.update(list);
    }

    @Override
    public void setHomeConsults(HomeConsults homeConsults) {
        consultsResultsEntities.clear();
        consultsResultsEntities.addAll(homeConsults.getResults());
        //此处用glide加载图片显示的不清楚
        Picasso.with(getActivity()).load(consultsResultsEntities.get(0).getAvatar()).into(avatar1);
        Picasso.with(getActivity()).load(consultsResultsEntities.get(1).getAvatar()).into(avatar2);
        Picasso.with(getActivity()).load(consultsResultsEntities.get(2).getAvatar()).into(avatar3);
        Picasso.with(getActivity()).load(consultsResultsEntities.get(3).getAvatar()).into(avatar4);
    }

    @Override
    public void setgetActionLabels(ActionLabelEntity labelEntity) {
        labelResultsEntities.clear();
        labelResultsEntities.addAll(labelEntity.getResults());
        adverView.setAdapter(adverViewAdapter);
        adverView.start();
    }

    @Override
    public void startActivity(Intent intent, int tag) {
        startActivityForResult(intent, tag);
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "position: " + position, Toast.LENGTH_SHORT).show();
    }
}