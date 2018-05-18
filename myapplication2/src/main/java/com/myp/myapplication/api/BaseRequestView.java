package com.myp.myapplication.api;

import com.myp.myapplication.mvp.BaseView;

/**
 * Created by Administrator on 2018/3/8.
 */

public interface BaseRequestView extends BaseView {
    void onRequestError(String msg);

    void onRequestEnd();
}
