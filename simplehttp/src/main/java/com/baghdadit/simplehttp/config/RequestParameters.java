package com.baghdadit.simplehttp.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestParameters {
    private Map<String, Object> parameters;

    public RequestParameters() {
        this.parameters = new HashMap<>();
    }

    public RequestParameters add(String key, Object value) {
        this.parameters.put(key, value);
        return this;
    }

    public Object get(String key) {
        return this.parameters.get(key);
    }

    public String getAsString(String key) {
        Object value = this.parameters.get(key);
        if (value == null)
            return null;
        return String.valueOf(value);
    }

    public Set<String> keys() {
        return this.parameters.keySet();
    }
}
