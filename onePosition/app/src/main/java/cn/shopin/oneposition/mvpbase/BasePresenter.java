package cn.shopin.oneposition.mvpbase;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import cn.shopin.oneposition.util.rxBus.RxBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by zcs on 2016/12/7.
 */
public class BasePresenter<V extends BaseView> {
    protected Reference<V> mReferenceView;

    protected CompositeDisposable mCompositeDisposable;


    protected V getView() {
        return mReferenceView.get();
    }

    private CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    protected void addSubscription(Disposable subscription) {
        getCompositeDisposable().add(subscription);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.getRxBusInstance().toDefaultFlowable(eventType, act));
    }

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void attachView(V view) {
        this.mReferenceView = new WeakReference<>(view);
    }

    public void detachView() {
        this.mReferenceView = null;
        unSubscribe();
    }

    /*-------------------------------------old BasePresenter------------------------------------------------*/

/*    public BaseObserver.handleDataCallBack callBack;

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
    }*/

/*    public interface handleDataCallBack {
        <T> T handData(T t, int typeTag);

        String error(String errorMessage);
    }*/
}
