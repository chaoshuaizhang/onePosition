package cn.shopin.oneposition.mvpbase;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by zcs on 2017/1/19.
 */

public class BaseObserver {
    public handleDataCallBack callBack;

    public <T> Observer<T> getObserver(final int typeTag) {
        Observer<T> observer = new Observer<T>() {

            @Override
            public void onError(Throwable e) {
                callBack.error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

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
