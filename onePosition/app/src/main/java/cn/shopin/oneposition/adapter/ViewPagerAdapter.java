package cn.shopin.oneposition.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zcs on 2016/12/12.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ImageView> imgs;

    public ViewPagerAdapter(Context mContext, List<ImageView> imgs) {
        this.mContext = mContext;
        this.imgs = imgs;
    }

    /**
     * 获取当前窗体界面数
     *
     * @return
     */
    @Override
    public int getCount() {
        return imgs.size();
    }

    /**
     * 用于判断是否由对象生成界面
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 这个return的view表明了PagerAdapter适配器选择这个放在当前的ViewPager中
     * 那么可以也对这个view进行一些设置
     *
     * @param container
     * @param position
     * @return view
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = imgs.get(position);
        container.addView(view);
        return view;
    }

    /**
     * 从ViewGroup中移出当前View
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(imgs.get(position));
    }
}
