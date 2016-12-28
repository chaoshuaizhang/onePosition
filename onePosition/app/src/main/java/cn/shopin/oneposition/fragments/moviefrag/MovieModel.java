package cn.shopin.oneposition.fragments.moviefrag;

import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.util.RetrofitUtil;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zcs on 2016/12/7.
 */
public class MovieModel implements MovieContract.IMovieModel {
    @Override
    public void getBanners() {
        Retrofit retrofit = RetrofitUtil.getRetrofitInstnce(0);
        MovieApi movieApi = retrofit.create(MovieApi.class);
        movieApi.getTopBanner("0", "10", "1", "0", "0", "1", "-1")
                .mergeWith(movieApi.getTopADBanner("0", "0", "10", "0"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
//                .subscribe(new Observer<List<BannerDetailEntity>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("TAG", "size : onError  " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(List<BannerDetailEntity> bannerDetailEntity) {
//                        Log.d("TAG", "size : " + bannerDetailEntity.size());
//                    }
//                })
        ;
    }

    @Override
    public void getBannerAds() {

    }
}
