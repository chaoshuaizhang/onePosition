package cn.shopin.oneposition.activities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zcs on 2017/7/10.
 */

public interface TestMovieDetail {
    @POST("{id}")
    Call<TestDetailBean<MvBean>> getTopMovie(@Path("id") long id);
}
