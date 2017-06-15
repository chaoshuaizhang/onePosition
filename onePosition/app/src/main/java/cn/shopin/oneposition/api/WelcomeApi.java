package cn.shopin.oneposition.api;

import cn.shopin.oneposition.entity.DataResultResponse;
import cn.shopin.oneposition.entity.movie.WelcomeEntity;
import dagger.Module;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zcs on 2017/4/19.
 */
@Module
public interface WelcomeApi {
    //    app欢迎页
    //    http://mrobot.pconline.com.cn/v2/cms/channels/11?pageSize=2&pageNo=1
    @GET("/v2/cms/channels/11")
    Flowable<WelcomeEntity> getWelcomeImgs(@Query("pageSize") int pageSize, @Query("pageNo") int pageNo);
}
