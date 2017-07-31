package cn.shopin.oneposition.api;

import java.util.Map;

import cn.shopin.oneposition.entity.BaseResponse;
import cn.shopin.oneposition.entity.UserEntity;
import dagger.Module;
import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zcs on 2017/7/12.
 */
@Module
public interface UserApi {
    /**
     * 注册接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Flowable<BaseResponse<Boolean>> userRegister(@FieldMap Map<String, Object> map);

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Flowable<BaseResponse<Boolean>> userLogin(@Field("username") String userName, @Field("userPwd") String userPwd);
}
