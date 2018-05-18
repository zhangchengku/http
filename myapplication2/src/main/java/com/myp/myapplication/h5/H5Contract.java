package com.myp.myapplication.h5;

import android.content.Context;

import com.myp.myapplication.mvp.BasePresenter;
import com.myp.myapplication.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class H5Contract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
