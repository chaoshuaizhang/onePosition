package cn.shopin.oneposition.api;

import cn.shopin.oneposition.entity.heart.HomeConsults;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zcs on 2017/2/21.
 *
 * @desc
 */
public interface HeartApi {
    @POST("consultant/loadRecommendedConsultants")
    Observable<HomeConsults> getHomeConsults();
}
