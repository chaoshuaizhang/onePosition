package cn.shopin.oneposition.util;

import cn.shopin.oneposition.entity.DataResultResponse;
import cn.shopin.oneposition.entity.movie.WelcomeEntity;
import cn.shopin.oneposition.mvpbase.BaseView;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zcs on 2017/6/13.
 * 通用的订阅者
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;

    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }

    @Override
    public void onNext(T tDataResultResponse) {
//        if (tDataResultResponse instanceof WelcomeEntity) {
            dataHandle(tDataResultResponse);
//        }
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

    /**
     * 抽取出来进行数据处理
     *
     * @param t
     */
    public abstract void dataHandle(T t);
}
