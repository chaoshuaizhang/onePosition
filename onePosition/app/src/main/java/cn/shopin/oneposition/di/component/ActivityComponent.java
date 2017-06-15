package cn.shopin.oneposition.di.component;

import android.app.Activity;

import cn.shopin.oneposition.activities.main.MainActivity;
import cn.shopin.oneposition.activities.welcome.WelcomeActivity;
import cn.shopin.oneposition.di.module.ActivityModule;
import cn.shopin.oneposition.di.module.AppModule;
import cn.shopin.oneposition.di.scope.ActivityScope;
import cn.shopin.oneposition.fragments.webdetail.MovieDetailActivity;
import dagger.Component;

/**
 * Created by zcs on 2017/4/19.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);

    void inject(MovieDetailActivity mvDetailActivity);
}
