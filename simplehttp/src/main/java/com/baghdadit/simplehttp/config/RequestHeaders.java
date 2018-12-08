package com.baghdadit.simplehttp.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestHeaders {
    private Map<String, String> headers;

    public RequestHeaders() {
        this.headers = new HashMap<>();
    }

    public String get(String key) {
        return this.headers.get(key);
    }

    public RequestHeaders add(String header, String value) {
        this.headers.put(header, value);
        return this;
    }

    public Set<String> keys() {
        return this.headers.keySet();
    }
}
