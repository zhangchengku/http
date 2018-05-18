package com.myp.myapplication.http;

import android.content.Context;
import android.util.Log;

import com.myp.myapplication.api.HttpInterfaceIml;
import com.myp.myapplication.api.UserBO;
import com.myp.myapplication.bean.MyHttpBO;
import com.myp.myapplication.mvp.BasePresenterImpl;

import rx.Subscriber;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HttpPresenter extends BasePresenterImpl<HttpContract.View> implements HttpContract.Presenter{


    @Override
    public void MyHttp(String a ,String b,String c) {
        HttpInterfaceIml.getMyHttp(a,b,null).subscribe(new Subscriber<UserBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("bug", "onError: "+"失败"+e.getMessage());
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(UserBO s) {
                Log.d("bug", "onNext: "+"成功"+s);
                if (mView == null)
                    return;
                mView.getMyHttp(s);
            }
        });
    }
    @Override
    public void tuiMyHttp() {
        HttpInterfaceIml.gettuiMyHttp().subscribe(new Subscriber<UserBO>() {
            @Override
            public void onCompleted() {
                if (mView == null)
                    return;
                mView.onRequestEnd();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("bug", "onError: "+"失败"+e.getMessage());
                if (mView == null)
                    return;
                mView.onRequestError(e.getMessage());
            }

            @Override
            public void onNext(UserBO s) {
                Log.d("bug", "onNext: "+"成功"+s);
                if (mView == null)
                    return;
                mView.getMyHttp(s);
            }
        });
    }


}
