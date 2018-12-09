package com.baghdadit.simplehttp;

import com.baghdadit.simplehttp.config.RequestConfig;
import com.baghdadit.simplehttp.config.RequestConfigAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class SimpleHttpClient {

    private RequestConfig config;

    public SimpleHttpClient(RequestConfig config) {
        this.config = config;
    }


    public String sendRequest() throws IOException {
        RequestConfigAdapter adapter = new RequestConfigAdapter(this.config);
        Call call = new OkHttpClient().newCall(adapter.toRequest());
        Response response = call.execute();
        if (response.body() != null) {
            return response.body().string();
        }
        return null;
    }

}