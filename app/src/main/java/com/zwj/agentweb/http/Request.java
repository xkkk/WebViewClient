package com.zwj.agentweb.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Request {


    /**
     * @param url 接口地址
     * @param map 接口参数
     * @return
     */
    @GET
    Observable<ResponseBody> methodGet(@Url String url, @QueryMap Map<String,String> map);


    @POST
    Observable<ResponseBody> methodPost(@Url String url, @PartMap Map<String,String> map);
}
