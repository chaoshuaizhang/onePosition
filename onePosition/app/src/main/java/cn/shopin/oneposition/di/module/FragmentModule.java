package cn.shopin.oneposition.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import cn.shopin.oneposition.di.scope.ActivityScope;
import cn.shopin.oneposition.di.scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zcs on 2017/4/19.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    /*
    * 下面的provide方法 是和FragmentComponent中的方法 一一对应的
    * 目前没发现有什么用
    *
    * */


    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @FragmentScope
    public Fragment provideFragment() {
        return mFragment;
    }

}