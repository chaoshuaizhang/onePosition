package cn.shopin.oneposition.di.module;

import android.app.Activity;

import cn.shopin.oneposition.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zcs on 2017/4/19.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}