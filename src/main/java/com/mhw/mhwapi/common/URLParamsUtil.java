package com.mhw.mhwapi.common;

import java.util.Map;

public class URLParamsUtil {

    public static String parseParam(Map<String, String> params, String key, String defaultValue) {
        String value = defaultValue;
        if (params.containsKey(key)) {
            value = params.get(key);
            params.remove(key);
        }
        return value;
    }

}
