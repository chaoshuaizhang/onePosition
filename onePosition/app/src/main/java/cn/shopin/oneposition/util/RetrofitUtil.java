package cn.shopin.oneposition.util;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.shopin.oneposition.constants.Cans;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zcs on 2016/12/16.
 *
 * @desc 网络请求工具类
 * 技能点：okhttp缓存策略
 */
public class RetrofitUtil {
    private static Retrofit retrofit = null;

    private static Retrofit getRetrofitInstnce(int tag) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MyInter())
                .connectTimeout(30, TimeUnit.SECONDS)//设置链接超时
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时
                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(okHttpClient)
                    .baseUrl(EnumServerMap.getBaseUrlByTag(tag))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static <S> S createService(Class<S> mClass, int tag) {
        return getRetrofitInstnce(tag).create(mClass);
    }
}

class MyInter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Log.d("TTAAAGG", "oldRequest : "+oldRequest.url().toString());
        return chain.proceed(oldRequest);
    }
}