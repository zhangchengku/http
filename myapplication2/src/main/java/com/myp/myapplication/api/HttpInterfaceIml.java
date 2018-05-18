package com.myp.myapplication.api;

import com.myp.myapplication.bean.MyHttpBO;

import rx.Observable;

/**
 * Created by Administrator on 2018/3/8.
 */

public class HttpInterfaceIml {

    private static HttpInterface service;

    /**
     * 获取代理对象
     */
    private static HttpInterface getService() {
        if (service == null)
            service = RetrofitManager.getInstance().createReq(HttpInterface.class);
        return service;
    }

    /**
     * 用户登陆
     */
    public static Observable<UserBO> getMyHttp(String a ,String b,String c) {
        return getService().getMyHttp(a,b,c).compose(RxResultHelper.<UserBO>io_main());
    }
    public static Observable<UserBO> gettuiMyHttp() {
        return getService().gettuiMyHttp().compose(RxResultHelper.<UserBO>io_main());
    }
}