package com.zwj.agentweb.util;

import android.text.TextUtils;

import com.zwj.agentweb.BuildConfig;
import com.zwj.agentweb.base.AppConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 作者：Android_AJ on 2017/4/11.
 * 邮箱：ai15116811712@163.com
 * 版本：v1.0
 * 与字符串相关的工具类
 */
public class AppStringUtils {

    /**
     * 获取asset目录下的配置文件信息
     *
     * @param property 属性名
     * @return
     */
    public static String getConfigProperties(String property) {
        InputStream in = null;
        SharedPreferencesUtil spUtils = SharedPreferencesUtil.getInstance();
        String buildType = spUtils.get("BUILD_TYPE", "");
        if (!TextUtils.isEmpty(buildType)
                && (TextUtils.equals(buildType, "debug") || TextUtils.equals(buildType, "dev") || TextUtils.equals(buildType, "release"))) {
            if (TextUtils.equals(buildType, "debug")) {
                in = AppStringUtils.class.getResourceAsStream(AppConstant.CONFIG_PROPERTIES_DEBUG);
            } else if (TextUtils.equals(buildType, "dev")) {
                in = AppStringUtils.class.getResourceAsStream(AppConstant.CONFIG_PROPERTIES_DEV);
            } else if (TextUtils.equals(buildType, "release")) {
                in = AppStringUtils.class.getResourceAsStream(AppConstant.CONFIG_PROPERTIES);
            }
        } else {
            if (BuildConfig.BUILD_TYPE.equals("debug")) {
                in = AppStringUtils.class.getResourceAsStream(AppConstant.CONFIG_PROPERTIES_DEBUG);
            } else if (BuildConfig.BUILD_TYPE.equals("dev")) {
                in = AppStringUtils.class.getResourceAsStream(AppConstant.CONFIG_PROPERTIES_DEV);
            } else {
                in = AppStringUtils.class.getResourceAsStream(AppConstant.CONFIG_PROPERTIES);
            }
        }

        Properties p = new Properties();
        String value = null;
        try {
            if (in == null) {
                return "";
            }
            p.load(in);
            value = p.getProperty(property);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (value != null) {
            value = value.trim();
        }
        return value;
    }
}

