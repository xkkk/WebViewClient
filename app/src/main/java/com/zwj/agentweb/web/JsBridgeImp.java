package com.zwj.agentweb.web;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.zwj.agentweb.R;

public class JsBridgeImp {
    BridgeWebView bridgeWebView;
    Activity context;
    public JsBridgeImp(BridgeWebView bridgeWebView, Activity context) {
        this.bridgeWebView = bridgeWebView;
        this.context = context;
    }

    public void setTitle(String title){

    }
}
