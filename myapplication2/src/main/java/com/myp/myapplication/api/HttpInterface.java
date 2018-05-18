package com.myp.myapplication.api;

import com.myp.myapplication.bean.MyHttpBO;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/3/8.
 */

public interface HttpInterface {
    @FormUrlEncoded
    @POST("/api/appuser/login")
    Observable<UserBO> getMyHttp(@Field("mobile") String mobile,
                                             @Field("password") String password, @Field("uuid") String uuid);
    @POST("/api/appuser/logout")
    Observable<UserBO> gettuiMyHttp();
}
