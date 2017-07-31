package cn.shopin.oneposition.di.module;

import android.util.Log;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import cn.shopin.oneposition.api.HeartApi;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.api.UserApi;
import cn.shopin.oneposition.api.WelcomeApi;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.di.qualifier.HeartUrl;
import cn.shopin.oneposition.di.qualifier.MovieUrl;
import cn.shopin.oneposition.di.qualifier.UserUrl;
import cn.shopin.oneposition.di.qualifier.WelcomeUrl;
import cn.shopin.oneposition.util.RetrofitUtil;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by zcs on 2017/4/19.
 */
@Module
public class HttpModule {
    /**
     * @return
     * @desc 提供OkHttpBuilder
     */
    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    /**
     * @return
     * @desc 提供OkHttpClient
     */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request oldRequest = chain.request();
                Log.d("REQ_URL", "old_url: " + oldRequest.url().toString());
                return chain.proceed(oldRequest);
            }
        });
        return builder.build();
    }

    /**
     * @return
     * @desc 提供RetrofitBuilder
     */
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    @MovieUrl
//和下边的provideGankService方法中的参数对应
    Retrofit provideMovieRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.createRetrofit(builder, client, Cans.TAG_MOVIE);
    }

    @Singleton
    @Provides
    MovieApi provideMovieService(@MovieUrl Retrofit retrofit) {
        return retrofit.create(MovieApi.class);
    }

    @Singleton
    @Provides
    @WelcomeUrl
    Retrofit provideWelcomeRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.createRetrofit(builder, client, Cans.TAG_OCEAN);
    }

    @Singleton
    @Provides
    WelcomeApi provideWelcomeService(@WelcomeUrl Retrofit retrofit) {
        return retrofit.create(WelcomeApi.class);
    }

    @Singleton
    @Provides
    @HeartUrl
    Retrofit provideHeartRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.createRetrofit(builder, client, Cans.TAG_HEART);
    }

    @Singleton
    @Provides
    HeartApi provideHeartService(@HeartUrl Retrofit retrofit) {
        return retrofit.create(HeartApi.class);
    }

    @Singleton
    @Provides
    @UserUrl
    Retrofit provideUserRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.createRetrofit(builder, client, Cans.TAG_USER);
    }
    @Singleton
    @Provides
    UserApi provideUserService(@UserUrl Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

}
