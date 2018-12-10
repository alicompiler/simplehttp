package com.baghdadit.simplehttp.config;

import java.io.File;

public class RequestConfigBuilder {
    private RequestConfig config;

    public RequestConfigBuilder() {
        this.config = new RequestConfig();
        this.config.setActionType(HttpActionType.GET);
        this.config.setHeaders(new RequestHeaders());
        this.config.setParameters(new RequestParameters());
        this.config.setAttachments(new RequestAttachments());
    }

    public RequestConfigBuilder url(String url) {
        this.config.setUrl(url);
        return this;
    }

    public RequestConfigBuilder actionType(HttpActionType actionType) {
        this.config.setActionType(actionType);
        return this;
    }

    public RequestConfigBuilder parameter(String key, Object value) {
        this.config.getParameters().add(key, value);
        return this;
    }

    public RequestConfigBuilder parameters(RequestParameters requestParameters) {
        this.config.setParameters(requestParameters);
        return this;
    }

    public RequestConfigBuilder header(String header, String value) {
        this.config.getHeaders().add(header, value);
        return this;
    }

    public RequestConfigBuilder headers(RequestHeaders headers) {
        this.config.setHeaders(headers);
        return this;
    }

    public RequestConfigBuilder attach(File file, String name, String mediaType) {
        this.config.getAttachments().add(file, name, mediaType);
        return this;
    }

    public RequestConfigBuilder attachments(RequestAttachments attachments) {
        this.config.setAttachments(attachments);
        return this;
    }

    public RequestConfig build() {
        return this.config;
    }

    public static RequestConfig simpleGETConfig(String url) {
        RequestConfigBuilder builder = new RequestConfigBuilder();
        builder.url(url);
        return builder.build();
    }

    public static RequestConfig simpleGETConfig(String url, RequestParameters parameters) {
        RequestConfigBuilder builder = new RequestConfigBuilder();
        builder.url(url);
        builder.parameters(parameters);
        return builder.build();
    }

    public static RequestConfig simplePOSTConfig(String url) {
        RequestConfigBuilder builder = new RequestConfigBuilder();
        builder.url(url);
        builder.actionType(HttpActionType.POST);
        return builder.build();
    }

    public static RequestConfig simplePOSTConfig(String url, RequestParameters parameters) {
        RequestConfigBuilder builder = new RequestConfigBuilder();
        builder.url(url);
        builder.parameters(parameters);
        builder.actionType(HttpActionType.POST);
        return builder.build();
    }


}
