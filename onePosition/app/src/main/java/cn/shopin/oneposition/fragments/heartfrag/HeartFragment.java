package cn.shopin.oneposition.fragments.heartfrag;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.squareup.picasso.Picasso;

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
import cn.shopin.oneposition.activities.main.MainActivity;
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
import cn.shopin.oneposition.util.PermissionsChecker;
import cn.shopin.oneposition.util.RetrofitUtil;
import cn.shopin.oneposition.util.ToastUtil;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zcs on 2016/12/5.
 */
public class HeartFragment extends BaseMvpFragment<HeartContract.IHeartView, HeartContract.IHeartPresenter> implements HeartContract.IHeartView {
    private View view;
    @BindView(R.id.circle_imageview)
    protected CircleImageView circleImageView;
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
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    private boolean isCamera = false;
    private Uri imageUri;//原图保存地址
    static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
    };

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
            view = inflater.inflate(R.layout.frag_heart, container, false);
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView != null) {//说明已经加载过了
            parentView.removeView(view);
            return view;
        }
        ButterKnife.bind(this, view);
        initView();
        initListener();
        initData();
        mPermissionsChecker = new PermissionsChecker(getActivity());
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
        circleImageView.setImageBitmap(getBitmapByPath(path + "head.jpg"));
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
                        openCamera();
                    }
                } else {
                    openCamera();
                }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    private void getPicFromAlbum() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, 1);
    }

    private void openCamera() {
        File file = new File(path + "head.jpg");
        Toast.makeText(getActivity(), file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(getActivity(), "cn.shopin.oneposition.fileprovider", file);//通过FileProvider创建一个content类型的Uri
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent, 2);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // 请求权限兼容低版本
    private void requestPer(String... permissions) {
        requestPermissions(permissions, 666);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 666: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        showMissingPermissionDialog();
                        return;
                    }
                }
                if (isCamera) {
                    openCamera();
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

    // 显示缺失权限提示
    private void showMissingPermissionDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("帮助");
        builder.setMessage("当前应用缺少必要权限");

        // 拒绝, 退出应用
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });
        builder.create().show();
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

    private Bitmap head;
    private static String path = Environment.getExternalStorageDirectory() + File.separator + "avatar" + File.separator;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (data != null && data.getData() != null)
                    cropPhoto(data.getData());// 裁剪图片
                break;
            case 2:
                final File temp = new File(path, "head.jpg");
                if (temp.exists()) {
                    Toast.makeText(getActivity(), "裁剪图存在" + temp.length(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "裁剪图不存在", Toast.LENGTH_LONG).show();
                }
                cropPhoto(imageUri);// 裁剪图片
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        // TODO: 2017/4/7 可以在此处上传服务器
                        Toast.makeText(getActivity(), "!= null", Toast.LENGTH_SHORT).show();
                        setPicToView(head);// 保存在SD卡中
                        circleImageView.setImageBitmap(head);// 用ImageView显示出来
                    } else {
                        Toast.makeText(getActivity(), "== null", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void cropPhoto(Uri uri) {
        File file = new File(path, "head.ipg");
        Uri outPutUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getBitmapByPath(String path) {
        File temp = new File(path);
        if (temp.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            return bitmap;
        } else {
            return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
    }
}