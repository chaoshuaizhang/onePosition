package cn.shopin.oneposition.fragments.heartfrag;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import cn.shopin.oneposition.Myapplication;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.entity.BaseResponse;
import cn.shopin.oneposition.entity.heart.ActionLabelEntity;
import cn.shopin.oneposition.entity.heart.HomeCarousel;
import cn.shopin.oneposition.entity.heart.HomeConsults;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.RxUtil;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by zcs on 2017/2/15.
 */

public class HeartPresenter extends BasePresenter<HeartContract.IHeartView> implements HeartContract.IHeartPresenter {
    private RetrofitHelper retrofitHelper;
    private Myapplication myApplication;
    private Uri imageUri;//原图保存地址
    private String path;

    @Inject
    public HeartPresenter(RetrofitHelper retrofitHelper, Myapplication myApplication) {
        this.retrofitHelper = retrofitHelper;
        this.myApplication = myApplication;
        path = Environment.getExternalStorageDirectory() + File.separator + "avatar" + File.separator;
    }

    @Override
    public void getTopBanner(String type) {
        addSubscription(retrofitHelper.getHeartHomeTopBanner(type)
                .compose(RxUtil.<HomeCarousel>rxSchedulerHelper())
                .subscribe(new Consumer<HomeCarousel>() {
                    @Override
                    public void accept(@NonNull HomeCarousel homeCarousel) throws Exception {
                        getView().setTopBanner(homeCarousel);
                    }
                }));
    }

    @Override
    public void getHomeConsults() {
        addSubscription(retrofitHelper.getHeartHomeConsults()
                .compose(RxUtil.<HomeConsults>rxSchedulerHelper())
                .subscribe(new Consumer<HomeConsults>() {
                    @Override
                    public void accept(@NonNull HomeConsults homeConsults) throws Exception {
                        Log.d("HomeConsults", homeConsults.getResults().size() + "");
                        getView().setHomeConsults(homeConsults);
                    }
                }));
    }

    @Override
    public void getActionLabels() {
        addSubscription(retrofitHelper.getHeartHomeLabels()
                .compose(RxUtil.<ActionLabelEntity>rxSchedulerHelper())
                .subscribe(new Consumer<ActionLabelEntity>() {
                    @Override
                    public void accept(@NonNull ActionLabelEntity labelEntity) throws Exception {
                        Log.d("HomeConsults", labelEntity.getResults().size() + "");
                        getView().setgetActionLabels(labelEntity);
                    }
                }));
    }

    @Override
    public void openCamera() {
        File file = new File(path + "head.jpg");
        Toast.makeText(myApplication.getApplicationContext(), file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(myApplication.getApplicationContext(), "cn.shopin.oneposition.fileprovider", file);//通过FileProvider创建一个content类型的Uri
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        getView().startActivity(intent, Cans.OPEN_CAMERA_RESULT);
    }

    // 显示缺失权限提示
    @Override
    public void showMissingPermissionDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(myApplication.getApplicationContext());
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
                //startAppSettings();
            }
        });
        builder.create().show();
    }

    @Override
    public void cropPhoto(Uri uri) {
        if (uri == null) {//null:拍照截图  !null:选择相册截图
            uri = imageUri;
        }
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
        getView().startActivity(intent, Cans.CROP_PHOTO_RESULT);
    }

    @Override
    public String getSavePath() {
        return path;
    }

    @Override
    public void saveBitmapoCard(Bitmap mBitmap) {
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

    @Override
    public Bitmap getBitmapByPath(String path) {
        File temp = new File(path);
        if (temp.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            return bitmap;
        } else {
            return BitmapFactory.decodeResource(myApplication.getResources(), R.mipmap.ic_launcher);
        }
    }
}
