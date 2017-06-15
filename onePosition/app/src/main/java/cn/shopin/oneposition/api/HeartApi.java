package cn.shopin.oneposition.api;

import cn.shopin.oneposition.entity.BaseResponse;
import cn.shopin.oneposition.entity.heart.ActionLabelEntity;
import cn.shopin.oneposition.entity.heart.HomeCarousel;
import cn.shopin.oneposition.entity.heart.HomeConsults;
import dagger.Module;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zcs on 2017/2/21.
 *
 * @desc
 */
@Module
public interface HeartApi {
    /**
     * @return
     * @desc 得到推荐的咨询师
     */
    @POST("consultant/loadRecommendedConsultants")
    Flowable<HomeConsults> getHomeConsults();

    @POST("com/loadActionLabels")
    Flowable<ActionLabelEntity> getActionLabels();

    @POST("com/loadBanner_v2")
    Flowable<HomeCarousel> getHomeTopBanner(@Query("type") String type);
}
