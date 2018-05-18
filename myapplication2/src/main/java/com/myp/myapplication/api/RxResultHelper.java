package com.myp.myapplication.api;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/8.
 */
public class RxResultHelper {

    private static final String TAG = "RxResultHelper";

    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable .subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread()); } }; }





}
