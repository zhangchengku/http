package com.myp.myapplication.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2018/3/8.
 */

public class RetrofitManager {
    private static RetrofitManager mRetrofitManager;
    private Retrofit mRetrofit;

    private RetrofitManager(){
        initRetrofit();
    }

    public static synchronized RetrofitManager getInstance(){

        if (mRetrofitManager == null){
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
        LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new RspCheckInterceptor());
        builder.addInterceptor(postInterceptor);

        if (AppConfig.DEBUG){
            builder.addInterceptor(LoginInterceptor);
        }

        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }


    Interceptor postInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            if (request.body() instanceof MultipartBody) {   //上传文件时，不进入此过滤器
                return chain.proceed(request);
            }
            String postBodyString = bodyToString(request.body());
            String params = "";
            if (postBodyString.indexOf("&") != -1) {
                String[] post = postBodyString.split("&");
                SortedMap<String, Object> keyAndValues = new TreeMap<>();
                if (post != null && post.length != 0) {
                    for (String param : post) {
                        String key = param.split("=")[0];
                        String value = param.split("=")[1];
                        keyAndValues.put(key, value);
                    }
                }
                for (String key : keyAndValues.keySet()) {
                    if ("_sig".equals(key)) {   //鼎新接口不需要加参数
                        return chain.proceed(request);
                    }
                    params += key + "=" + keyAndValues.get(key) + "&";
                }
            }
            //避免中文生成签名时和后台的编码冲突
            String encodeParm = StringUtils.isEmpty(params) ? postBodyString : params.substring(0, params.length() - 1);
            String sign = MD5.strToMd5Low32(URLEncoder.encode(URLDecoder.decode(encodeParm, "UTF-8"), "UTF-8"));
            RequestBody formBody = new FormBody.Builder()
                    .add("sign", sign)
                    .build();
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
            request = requestBuilder
                    .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"),
                            postBodyString))
                    .build();
            return chain.proceed(request);
        }
    };
    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
    public <T> T createReq(Class<T> reqServer){
        return mRetrofit.create(reqServer);
    }
}
