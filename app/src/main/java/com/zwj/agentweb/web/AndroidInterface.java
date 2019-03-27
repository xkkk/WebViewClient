package com.zwj.agentweb.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.LogUtils;
import com.zwj.agentweb.BuildConfig;
import com.zwj.agentweb.CustomApplication;
import com.zwj.agentweb.activity.ScanActivity;
import com.zwj.agentweb.base.AppConstant;
import com.zwj.agentweb.http.HttpManager;
import com.zwj.agentweb.util.CommonUtils;
import com.zwj.agentweb.util.Json2Map;
import com.zwj.agentweb.util.UiUtil;
import com.zwj.agentweb.widget.CommonDialogUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by xukun on 2019/3/14.
 *  source code  https://github.com/Justson/AgentWeb
 */

public class AndroidInterface {
    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Activity context;

    public AndroidInterface(AgentWeb agent, Activity context) {
        this.agent = agent;
        this.context = context;
    }





    /**
     * h5页面返回
     */
    @JavascriptInterface
    public void back() {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                context.finish();
            }
        });
    }


    /**
     * h5页面返回,带有返回参数
     */
    @JavascriptInterface
    public void back(Object message) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                context.finish();
            }
        });
    }

    /**
     * web 点击唤醒本地应用链接
     *
     * @param wakeUpLink
     * @param APPName
     */
    @JavascriptInterface
    public void wakeUpAPP(final String wakeUpLink, String APPName) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                CommonUtils.openOtherAppByPackageName(context, wakeUpLink);
            }
        });
    }

    /**
     * 显示弹窗
     */
    @JavascriptInterface
    public void alertMessage(final String message, final String callback) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                CommonDialogUtils.showOnlyConfirmDialog(context, message, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!TextUtils.isEmpty(callback)) {
                            agent.getJsAccessEntrace().quickCallJs(callback, "");
                        }
                    }
                });
            }
        });
    }
    /**
     * 显示弹窗
     */
    @JavascriptInterface
    public void alertMessage(final String message) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                CommonDialogUtils.showOnlyConfirmDialog(context, message, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });
    }

    /**
     * 打开智能人
     */
    @JavascriptInterface
    public void openIntelligentManApp(final String packageName) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
                CommonUtils.openOtherAppByPackageName(context, packageName);
            }
        });

    }

    @JavascriptInterface
    public void callAndroid(String message){
        UiUtil.showToast(context,message);
    }


    @JavascriptInterface
    public void getData(String method, String param, final String callbackFunction){
        Map<String,String> map = new HashMap<>();
        String baseUrl = AppConstant.BASE_URL;
        String url =baseUrl+"weather_mini?";
        map.put("city","北京");
        Disposable subscribe = HttpManager.getRequest()
                .methodGet(url, map)
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String data = responseBody.string();
                        UiUtil.showToast(CustomApplication.getContext(), data);
                        agent.getJsAccessEntrace().quickCallJs(callbackFunction, data);
                    }
                });
    }



    @JavascriptInterface
    public void goToScan(){
        Intent intent = new Intent(context, ScanActivity.class);
        context.startActivity(intent);
    }

    @JavascriptInterface
    public void goUrl(String url){
        agent.getUrlLoader().loadUrl(url);
    }


    @JavascriptInterface
    public void getSystemInfo(){
        String packageName = BuildConfig.APPLICATION_ID;
    }

    /**
     * 打开手机默认浏览器
     *
     * @param url
     */
    @JavascriptInterface
    public boolean openDefaultBrowser(String url) {
        return CommonUtils.openWeb(context, url);
    }


    @JavascriptInterface
    public void showHtmlCode(String html){
        LogUtils.i("cme","网页源码： "+html);
    }



}
