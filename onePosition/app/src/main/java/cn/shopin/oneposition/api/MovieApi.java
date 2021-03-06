package cn.shopin.oneposition.api;

import java.util.List;

import cn.shopin.oneposition.entity.movie.BannerDetailEntity;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.entity.movie.PieceEntity;
import dagger.Module;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zcs on 2016/12/16.
 *
 * @desc
 */
@Module
public interface MovieApi {
    public static String MOVIE_DETAIL = "http://moviewapp.dazui.com/Article/Detailv2?id=";

    /**
     * @param fromTime  0代表今天，昨天的时间是昨天凌晨0点的时间戳(13位)
     * @param count     请求数量
     * @param category  默认 1
     * @param type      默认 0
     * @param subtype   默认 1
     * @param recommend 默认 1
     * @param haslinks  默认 -1
     * @return
     */
    @GET("/APIV3/Article/GetList")
    Flowable<List<BannerDetailEntity>> getTopBanner(@Query("fromTime") String fromTime,
                                                    @Query("count") String count,
                                                    @Query("category") String category,
                                                    @Query("type") String type,
                                                    @Query("subtype") String subtype,
                                                    @Query("recommend") String recommend,
                                                    @Query("haslinks") String haslinks);

    /**
     * @param fromTime
     * @param userId   默认 0
     * @param count
     * @param type
     * @return
     */
    @GET("/APIV3/Vip/GetActivityList")
    Observable<List<BannerDetailEntity>> getTopADBanner(@Query("fromTime") String fromTime,
                                                        @Query("userId") String userId,
                                                        @Query("count") String count,
                                                        @Query("type") String type);

    /**
     * 怀旧
     *
     * @param fromTime
     * @param count
     * @param category
     * @param type
     * @param subtype
     * @return
     */
    //http://moviewapp.dazui.com/APIV3/Article/GetList?fromTime=1469007382000&count=10&category=2&type=0&subtype=39
    @GET("/APIV3/Article/GetList")
    Flowable<List<NostalgicEntity>> getMovieNostalgic(@Query("fromTime") String fromTime,
                                                      @Query("count") String count,
                                                      @Query("category") String category,
                                                      @Query("type") String type,
                                                      @Query("subtype") String subtype);

    /**
     * @param timestamp
     * @return
     * @desc 排片
     */
    @GET("/MovieSheet/GetDataByDate")
    Flowable<List<PieceEntity>> getMoviePieceEntity(@Query("timestamp") String timestamp);


}
//HTTP 405 Method Not Allowed post换成get即可，说明服务器只支持get请求
