package cn.shopin.oneposition.activities.welcome;

import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.entity.DataResultResponse;
import cn.shopin.oneposition.entity.movie.WelcomeEntity;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.CommonSubscriber;
import cn.shopin.oneposition.util.RxUtil;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by zcs on 2017/4/13.
 */

public class WelcomePresenter extends BasePresenter<WelcomeContract.IWelcomeView> implements WelcomeContract.IWelcomePresenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public WelcomePresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void setWelcomeImg() {
        addSubscription(mRetrofitHelper.getWelcomeImg(1, 1)
                .compose(RxUtil.<WelcomeEntity>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<WelcomeEntity>(getView()) {
                    @Override
                    public void dataHandle(WelcomeEntity welcomeEntity) {
                        getView().setImgs(welcomeEntity);
                    }
                }));
    }

    /**
     * @desc 取代Handler进行页面延时跳转
     */
    @Override
    public void actionToMainActivity() {
        addSubscription(Flowable.timer(Cans.ACTION_TO_MAINACTIVITY_SECONDS, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        getView().jumpToMain();
                    }
                })
        );
    }


/*
    @Override
    public <T> T handData(T t, int typeTag) {
        switch (typeTag) {
            case 1:
                WelcomeEntity welcomeEntity = (WelcomeEntity) t;
                getView().setImgs(welcomeEntity);
                break;
        }
        return null;
    }

    @Override
    public String error(String errorMessage) {
        return null;
    }*/

    /*----------------------------------------old 网络请求----------------------------------------------*/
/*    public void setWelcomeImg___old() {
        addSubscription(mRetrofitHelper.getWelcomeImg(1, 1)
                .compose(RxUtil.<WelcomeEntity>rxSchedulerHelper())
                .subscribe(new Consumer<WelcomeEntity>() {
                    @Override
                    public void accept(@NonNull WelcomeEntity welcomeEntity) throws Exception {
                        getView().setImgs(null);
                        waitToMain();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("WelcomePresenter", "accept --- error");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("WelcomePresenter", "accept --- complete");
                    }
                }));
    }*/

}


