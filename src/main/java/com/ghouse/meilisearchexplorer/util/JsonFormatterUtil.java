package com.ghouse.meilisearchexplorer.util;

import org.json.JSONObject;
import org.json.JSONException;

public class JsonFormatterUtil {

    public static String formatJson(String rawString) throws JSONException {
        JSONObject jsonObject = new JSONObject(rawString);
        return jsonObject.toString(4);
    }

}