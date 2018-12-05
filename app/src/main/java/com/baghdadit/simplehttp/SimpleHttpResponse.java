package com.baghdadit.simplehttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SimpleHttpResponse {
    private String content;

    public SimpleHttpResponse(String response) {
        this.content = response;
    }

    public String asString() {
        return this.content;
    }

    public JSONObject asJsonObject() throws JSONException {
        return new JSONObject(this.content);

    }

    public JSONArray asJsonArray() throws JSONException {
        return new JSONArray(this.content);
    }
}
