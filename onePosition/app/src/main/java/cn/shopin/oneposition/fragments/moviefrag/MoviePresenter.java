package cn.shopin.oneposition.fragments.moviefrag;

import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.entity.movie.BannerDetailEntity;
import cn.shopin.oneposition.model.db.DBManager;
import cn.shopin.oneposition.model.net.RetrofitHelper;
import cn.shopin.oneposition.mvpbase.BasePresenter;
import cn.shopin.oneposition.util.EnumServerMap;
import cn.shopin.oneposition.util.RxUtil;
import cn.shopin.oneposition.util.rxBus.EventEntity;
import cn.shopin.oneposition.util.rxBus.RxBus;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by zcs on 2016/12/7.
 * 如果不是模块间通信的话，不必用RxBus
 */
public class MoviePresenter extends BasePresenter<MovieContract.IMovieView> implements MovieContract.IMoviePresenter {
    private RetrofitHelper mRetrofitHelper;
    private DBManager dbManager;
    private Disposable disposable = RxBus.getRxBusInstance()
            .toFlowable(EventEntity.class)
            .subscribe(new Consumer<EventEntity>() {
                @Override
                public void accept(@NonNull EventEntity eventEntity) throws Exception {
                }
            });

    @Inject
    public MoviePresenter(RetrofitHelper retrofitHelper, DBManager dbManager) {
        mRetrofitHelper = retrofitHelper;
        this.dbManager = dbManager;
    }

    @Override
    public void getBanners() {
        addSubscription(mRetrofitHelper.getTopBanner("0", "10", "1", "0", "0", "1", "-1")
                .compose(RxUtil.<List<BannerDetailEntity>>rxSchedulerHelper())
                .subscribe(new Consumer<List<BannerDetailEntity>>() {
                    @Override
                    public void accept(@NonNull List<BannerDetailEntity> bannerDetailEntities) throws Exception {
                        getView().getBannerData(bannerDetailEntities);
                    }
                }));
    }

    @Override
    public void getBannerAds() {

    }
}
