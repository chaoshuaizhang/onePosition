package cn.shopin.oneposition.fragments.moviefrag;

import android.util.Log;

import java.util.List;

import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.entity.movie.BannerDetailEntity;
import cn.shopin.oneposition.util.EnumServerMap;
import cn.shopin.oneposition.util.rxBus.EventEntity;
import cn.shopin.oneposition.util.rxBus.RxBus;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by zcs on 2016/12/7.
 */
public class MoviePresenter extends MovieContract.IMoviePresenter {
    private MovieContract.IMovieView movieView;
    private MovieContract.IMovieModel movieModel;
    private Subscription subscription = RxBus.getRxBusInstance()
            .tObservable(EventEntity.class)
            .subscribe(new Action1<EventEntity>() {
                @Override
                public void call(EventEntity eventEntity) {
                    if (eventEntity.getTag() == 123) {
                        List<BannerDetailEntity> list = (List<BannerDetailEntity>) eventEntity.getObject();
                        movieView.getBannerData(list);
                    }
                }
            });

    public MoviePresenter(MovieContract.IMovieView iMovieView) {
        super(iMovieView);
        movieView = iMovieView;
        movieModel = new MovieModel();
    }

    @Override
    void getBanners() {
        movieModel.getBanners();
    }

    @Override
    void getBannerAds() {

    }
}
