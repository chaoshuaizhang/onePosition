package cn.shopin.oneposition.fragments.moviefrag;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.constants.Cans;
import cn.shopin.oneposition.entity.movie.BannerDetailEntity;
import cn.shopin.oneposition.mvpbase.BaseObserver;
import cn.shopin.oneposition.util.RetrofitUtil;
import cn.shopin.oneposition.util.rxBus.EventEntity;
import cn.shopin.oneposition.util.rxBus.RxBus;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zcs on 2016/12/7.
 */
public class MovieModel extends BaseObserver implements MovieContract.IMovieModel, BaseObserver.handleDataCallBack {

    public MovieModel() {
        super();
        callBack = this;
    }

    @Override
    public void getBanners() {
        MovieApi movieApi = RetrofitUtil.createService(MovieApi.class, Cans.TAG_MOVIE);
        movieApi.getTopBanner("0", "10", "1", "0", "0", "1", "-1")
                // TODO: 2017/2/8 普通banner 和 广告 对象不一样 
//                .mergeWith(movieApi.getTopADBanner("0", "0", "10", "0"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.<List<BannerDetailEntity>>getObserver(123));
    }

    @Override
    public void getBannerAds() {

    }

    @Override
    public <T> T handData(T t, int typeTag) {
        switch (typeTag) {
            case 123:
                List<BannerDetailEntity> list = (List<BannerDetailEntity>) t;
                // TODO: 2017/1/19 rxJava发送
                RxBus.getRxBusInstance().post(new EventEntity(123, list));
                break;
        }
        return null;
    }

    @Override
    public String error(String errorMessage) {
        return null;
    }
}
