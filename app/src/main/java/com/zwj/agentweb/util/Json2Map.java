package com.zwj.agentweb.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于转化JavaScript传过来的Json字符串
 */
public class Json2Map {

    public static Map<String,String> parseParam(String jsonStr){
        return new Gson().fromJson(jsonStr,Map.class);
    }

}
