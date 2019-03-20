package com.zwj.agentweb.http;

import com.zwj.agentweb.base.AppConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager sInsantce;
    private static Retrofit retrofit;
    private static Request request;
    private static final int TIMEOUT = 60;

    public static HttpManager getInstance(){
        if (sInsantce == null) {
            synchronized (HttpManager.class){
                if (sInsantce==null){
                    sInsantce = new HttpManager();
                }
            }
        }
        return sInsantce;
    }
    public void init(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(AppConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Request getRequest(){
        if (request == null) {
            synchronized (Request.class){
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }
}
