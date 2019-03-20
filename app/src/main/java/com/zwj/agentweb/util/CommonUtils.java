package com.zwj.agentweb.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

/**
 * 作者：Android_AJ on 2017/4/7.
 * 邮箱：ai15116811712@163.com
 * 版本：v1.0
 * 通用工具类 1.给RecyclerView设置分割线，2.获取版本号，3.判断Service是否在运行
 */
public class CommonUtils {
    /**
     * 获取VersionCode
     *
     * @return
     */
    public static String getVersionCode(Context context) {
        String result = "";
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            result = String.valueOf(packageInfo.versionCode);
            return result;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取VersionName
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String result = "";
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            result = String.valueOf(packageInfo.versionName);
            return result;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
            if (serviceList == null || serviceList.isEmpty())
                return false;
            for (int i = 0; i < serviceList.size(); i++) {
                if (serviceList.get(i).service.getClassName().equals(className) && TextUtils.equals(
                        serviceList.get(i).service.getPackageName(), context.getPackageName())) {
                    isRunning = true;
                    break;
                }
            }
        }
        return isRunning;
    }

    /**
     * 跳到应用市场应用详情
     *
     * @param activity
     */
    public static void openAppStore(Activity activity) {
        //这里开始执行一个应用市场跳转逻辑，默认this为Context上下文对象
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + activity.getApplicationContext().getPackageName())); //跳转到应用市场，非Google Play市场一般情况也实现了这个接口
        //存在手机里没安装应用市场的情况，跳转会包异常，做一个接收判断
        if (intent.resolveActivity(activity.getApplicationContext().getPackageManager()) != null) { //可以接收
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity.getApplicationContext(),"您的系统中没有安装应用市场", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean openWeb(Activity activity, String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        it.addCategory(Intent.CATEGORY_BROWSABLE);
        activity.startActivity(it);
        return true;
    }

    /**
     * 根据包名打开其他应用
     *
     * @param activity    activity
     * @param packageName 包名
     */
    public static void openOtherAppByPackageName(Activity activity, String packageName) {
        try {
            Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "检查你是否安装应用", Toast.LENGTH_SHORT).show();
        }
    }

}
