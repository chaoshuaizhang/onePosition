package cn.shopin.oneposition.model.net;

import java.util.List;

import javax.inject.Inject;

import cn.shopin.oneposition.api.HeartApi;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.api.WelcomeApi;
import cn.shopin.oneposition.entity.BaseResponse;
import cn.shopin.oneposition.entity.DataResultResponse;
import cn.shopin.oneposition.entity.heart.ActionLabelEntity;
import cn.shopin.oneposition.entity.heart.HomeCarousel;
import cn.shopin.oneposition.entity.heart.HomeConsults;
import cn.shopin.oneposition.entity.movie.BannerDetailEntity;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.entity.movie.PieceEntity;
import cn.shopin.oneposition.entity.movie.WelcomeEntity;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by zcs on 2017/4/19.
 */

public class RetrofitHelper {
    private MovieApi movieApi;
    private WelcomeApi welcomeApi;
    private HeartApi heartApi;

    public RetrofitHelper(MovieApi movieApi, WelcomeApi welcomeApi, HeartApi heartApi) {
        this.movieApi = movieApi;
        this.welcomeApi = welcomeApi;
        this.heartApi = heartApi;
    }

    public Flowable<List<BannerDetailEntity>> getTopBanner(String fromTime, String count, String category, String type, String subtype, String recommend, String haslinks) {
        return movieApi.getTopBanner(fromTime, count, category, type, subtype, recommend, haslinks);
    }

    public Flowable<WelcomeEntity> getWelcomeImg(int pageSize, int pageNo) {
        return welcomeApi.getWelcomeImgs(pageSize, pageNo);
    }

    public Flowable<List<NostalgicEntity>> getMovieNostalgic(String fromTime, String count, String category, String type, String subtype) {
        return movieApi.getMovieNostalgic(fromTime, count, category, type, subtype);
    }

    public Flowable<List<PieceEntity>> getMoviePieceEntities(String timeStr) {
        return movieApi.getMoviePieceEntity(timeStr);
    }

    public Flowable<HomeCarousel> getHeartHomeTopBanner(String type) {
        return heartApi.getHomeTopBanner(type);
    }

    public Flowable<HomeConsults> getHeartHomeConsults() {
        return heartApi.getHomeConsults();
    }

    public Flowable<ActionLabelEntity> getHeartHomeLabels() {
        return heartApi.getActionLabels();
    }
}
