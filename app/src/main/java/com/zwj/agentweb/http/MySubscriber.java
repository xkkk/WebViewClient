package com.zwj.agentweb.http;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MySubscriber implements Subscriber<ResponseBody> {


    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String data = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
