package com.myp.myapplication.api;

import android.util.Log;

import com.myp.myapplication.my;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/3/8.
 */

public class RspCheckInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        Log.d("请求头", "=== "+response.headers().get("Set-Cookie"));
            String cookies = response.headers().get("Set-Cookie");
        if (cookies != null) {
            String cookie[] = cookies.split(";");
            if (cookie != null && cookie.length != 0) {
                if (cookie[0].split("=") != null && cookie[0].split("=").length != 0) {
                    my.SESSIONID = cookie[0].split("=")[1];

                }
            }
        }
//        try
//        {
//            ResponseBody rspBody = response.body();
//            JSONObject jsonObject = new JSONObject(InterceptorUtils.getRspData(rspBody));
//            int status = jsonObject.getInt("status");
//            if (status < 200 || status >= 300){
//                throw new IOException(jsonObject.getString("msg"));
//            }
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            throw new IOException("parase data error");
//        }catch (Exception e){
//            if (e instanceof IOException){
//                throw (IOException)e;
//            }
//        }
        return response;
    } }

