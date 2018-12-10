package com.baghdadit.simplehttp;

import com.baghdadit.simplehttp.config.HttpActionType;
import com.baghdadit.simplehttp.config.RequestConfig;
import com.baghdadit.simplehttp.config.RequestConfigBuilder;
import com.baghdadit.simplehttp.listeners.OnComplete;
import com.baghdadit.simplehttp.listeners.OnError;

import java.io.File;
import java.io.IOException;

public class SimpleHttp {
    private RequestConfigBuilder builder;
    private OnComplete onComplete;
    private OnError onError;

    public SimpleHttp() {
        this.builder = new RequestConfigBuilder();
    }

    public SimpleHttp url(String url) {
        this.builder.url(url);
        return this;
    }

    public SimpleHttp method(HttpActionType method) {
        this.builder.actionType(method);
        return this;
    }

    public SimpleHttp parameter(String key, Object value) {
        this.builder.parameter(key, value);
        return this;
    }

    public SimpleHttp header(String header, String value) {
        this.builder.header(header, value);
        return this;
    }

    public SimpleHttp attach(File file, String name) {
        this.builder.attach(file, name, null);
        return this;
    }

    public SimpleHttp attach(File file, String name, String mediaType) {
        this.builder.attach(file, name, mediaType);
        return this;
    }

    public SimpleHttp onComplete(OnComplete onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public SimpleHttp onError(OnError onError) {
        this.onError = onError;
        return this;
    }


    public void sendRequest() throws IOException {
        RequestConfig config = this.builder.build();
        SimpleHttpClient client = new SimpleHttpClient(config);
        client.sendRequest();
    }

    public void sendRequestAsync() {
        RequestConfig config = this.builder.build();
        AsyncSimpleHttpClient client = new AsyncSimpleHttpClient(config, this.onComplete, this.onError);
        client.sendRequest();
    }
}
