package cn.shopin.oneposition.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zcs on 2017/3/4.
 */

public class FragPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragsList;

    public FragPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragsList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragsList.get(position);
    }

    @Override
    public int getCount() {
        return fragsList.size();
    }

    /**
     * 无需重写以下两个方法
     * 因为是FragmentPagerAdapter
     */
/*    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }*/
}
