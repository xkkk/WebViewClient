package com.zwj.agentweb.web;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.LogUtils;
import com.zwj.agentweb.R;

public class LayoutInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Activity context;

    public LayoutInterface(AgentWeb agent, Activity context) {
        this.agent = agent;
        this.context = context;
    }

    @JavascriptInterface
    public void hideTitleBar(){
        deliver.post(new Runnable() {
            @Override
            public void run() {
                Toolbar toolbar = context.findViewById(R.id.toolbar);
                toolbar.setVisibility(View.GONE);
            }
        });
    }

    @JavascriptInterface
    public void showTitleBar(){
        deliver.post(new Runnable() {
            @Override
            public void run() {
                Toolbar toolbar = context.findViewById(R.id.toolbar);
                toolbar.setVisibility(View.VISIBLE);
            }
        });
    }
    @JavascriptInterface
    public void setTitle(final String title){
        LogUtils.i("cme","title = "+title);
        deliver.post(new Runnable() {
            @Override
            public void run() {
                TextView toolbar = context.findViewById(R.id.toolbar_title);
                toolbar.setText(title);
            }
        });
    }

    @JavascriptInterface
    public void setTitleColor(final String color){
        LogUtils.i("cme","color = "+color);
        deliver.post(new Runnable() {
            @Override
            public void run() {
                TextView toolbar = context.findViewById(R.id.toolbar_title);
                toolbar.setTextColor(Color.parseColor(color));
            }
        });
    }
    @JavascriptInterface
    public void setTitleBarColor(final String color){
        LogUtils.i("cme","color = "+color);
        deliver.post(new Runnable() {
            @Override
            public void run() {
                Toolbar toolbar = context.findViewById(R.id.toolbar_title);
                toolbar.setBackgroundColor(Color.parseColor(color));
            }
        });
    }
}
