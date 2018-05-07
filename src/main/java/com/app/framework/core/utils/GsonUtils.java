package com.app.framework.core.utils;

import com.google.gson.Gson;

public final class GsonUtils {

    public static <T> T fromJson(String text, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(text, cls);
    }

    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
