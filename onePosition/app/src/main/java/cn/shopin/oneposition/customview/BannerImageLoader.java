package cn.shopin.oneposition.customview;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by zcs on 2017/5/5.
 */

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load(path.toString()).into(imageView);
    }
}
