package com.baghdadit.simplehttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleHttpResponse {
    private String content;

    public SimpleHttpResponse(String response) {
        this.content = response;
    }

    public String asString() {
        return this.content;
    }

    public JSONObject asJsonObject() {
        try {
            return new JSONObject(this.content);
        } catch (JSONException e) {
            return null;
        }
    }

    public JSONArray asJsonArray() {
        try {
            return new JSONArray(this.content);
        } catch (JSONException e) {
            return null;
        }
    }
}
