package cn.shopin.oneposition.di.module;

import javax.inject.Singleton;

import cn.shopin.oneposition.Myapplication;
import cn.shopin.oneposition.api.HeartApi;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.api.WelcomeApi;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by zcs on 2017/4/19.
 */
@Module
public class AppModule {

    private final Myapplication mApplication;

    public AppModule(Myapplication mApplication) {
        this.mApplication = mApplication;
    }

    /**
     * @return
     * @desc 对应AppConponent
     */
    @Singleton
    @Provides
    Myapplication provideApplicationContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    RetrofitHelper provideRetrofitHelper(MovieApi movieApi, WelcomeApi welcomeApi, HeartApi heartApi) {
        return new RetrofitHelper(movieApi, welcomeApi, heartApi);
    }
}
