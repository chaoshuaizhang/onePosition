package cn.shopin.oneposition.api;

import cn.shopin.oneposition.entity.heart.ActionLabelEntity;
import cn.shopin.oneposition.entity.heart.HomeConsults;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zcs on 2017/2/21.
 *
 * @desc
 */
public interface HeartApi {
    /**
     * @return
     * @desc 得到推荐的咨询师
     */
    @POST("consultant/loadRecommendedConsultants")
    Observable<HomeConsults> getHomeConsults();

    @POST("com/loadActionLabels")
    Observable<ActionLabelEntity> getActionLabels();
}
