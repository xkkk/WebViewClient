package com.zwj.agentweb;

import android.app.Application;
import android.content.Context;

import com.zwj.agentweb.http.HttpManager;

public class CustomApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        HttpManager.getInstance().init();
    }

    public static Context getContext(){
        return context;
    }
}
