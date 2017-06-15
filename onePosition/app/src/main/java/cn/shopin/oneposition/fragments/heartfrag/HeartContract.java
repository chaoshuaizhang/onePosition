package cn.shopin.oneposition.fragments.heartfrag;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

import cn.shopin.oneposition.entity.heart.ActionLabelEntity;
import cn.shopin.oneposition.entity.heart.HomeCarousel;
import cn.shopin.oneposition.entity.heart.HomeConsults;
import cn.shopin.oneposition.mvpbase.BaseModel;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.mvpbase.BaseView;

/**
 * Created by zcs on 2017/2/15.
 */

public interface HeartContract {
    interface IHeartModel extends BaseModel {
    }

    interface IHeartView extends BaseView {
        void setTopBanner(HomeCarousel homeCarousel);

        void setHomeConsults(HomeConsults homeConsults);

        void setgetActionLabels(ActionLabelEntity labelEntity);

        void startActivity(Intent intent, int tag);
    }

    interface IHeartPresenter {

        void getTopBanner(String type);

        void getHomeConsults();

        void getActionLabels();

        Bitmap getBitmapByPath(String path);

        void saveBitmapoCard(Bitmap mBitmap);

        void openCamera();

        void showMissingPermissionDialog();

        void cropPhoto(Uri uri);

        String getSavePath();
    }
}
