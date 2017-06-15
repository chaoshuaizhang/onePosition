package cn.shopin.oneposition.util;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zcs on 2017/4/29.
 */

public class RxUtil {
    /**
     * 统一线程处理
     * compose简化线程
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
