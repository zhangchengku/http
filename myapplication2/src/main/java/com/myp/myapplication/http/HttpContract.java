package com.myp.myapplication.http;

import android.content.Context;

import com.myp.myapplication.api.BaseRequestView;
import com.myp.myapplication.api.UserBO;
import com.myp.myapplication.bean.MyHttpBO;
import com.myp.myapplication.mvp.BasePresenter;
import com.myp.myapplication.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HttpContract {
    interface View extends BaseRequestView {
        void getMyHttp(UserBO myHttpBO);
    }

    interface  Presenter extends BasePresenter<View> {
        void MyHttp(String a ,String b,String c);
        void tuiMyHttp();

    }
}
