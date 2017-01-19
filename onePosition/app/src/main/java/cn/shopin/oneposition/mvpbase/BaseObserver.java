package cn.shopin.oneposition.mvpbase;

import android.util.Log;

import com.google.gson.Gson;

import rx.Observer;

/**
 * Created by zcs on 2017/1/19.
 */

public class BaseObserver {
    public handleDataCallBack callBack;

    public <T> Observer<T> getObserver(final int typeTag) {
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callBack.error(e.getMessage());
            }

            @Override
            public void onNext(T t) {
                callBack.handData(t, typeTag);
            }
        };
        return observer;
    }

    public interface handleDataCallBack {
        <T> T handData(T t, int typeTag);

        String error(String errorMessage);
    }

}
