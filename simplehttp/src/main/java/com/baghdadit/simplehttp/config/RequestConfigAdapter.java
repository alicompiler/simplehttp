package com.baghdadit.simplehttp.config;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestConfigAdapter {
    private RequestConfig requestConfig;
    private Request.Builder requestBuilder;
    private HttpUrl httpUrl;
    private MultipartBody.Builder multipartBodyBuilder;

    public RequestConfigAdapter(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
        this.requestBuilder = new Request.Builder();
        this.multipartBodyBuilder = new MultipartBody.Builder();
    }

    public Request toRequest() {
        this.httpUrl = HttpUrl.parse(requestConfig.getUrl());
        this.appendHeaders();
        if (this.requestConfig.getActionType() == HttpActionType.POST) {
            this.appendPOSTParameters();
            this.appendAttachments();
            this.requestBuilder.post(multipartBodyBuilder.build());
        } else {
            this.appendGETParameters();
        }
        return requestBuilder.build();
    }

    private void appendAttachments() {
        for (Attachment attachment : this.requestConfig.getAttachments().all()) {
            MediaType mediaType = attachment.getMediaType() == null ? null : MediaType.parse(attachment.getMediaType());
            this.multipartBodyBuilder.addFormDataPart(attachment.getName(), null, RequestBody.create(mediaType, attachment.getFile()));
        }
    }

    private void appendHeaders() {
        RequestHeaders headers = this.requestConfig.getHeaders();
        for (String key : headers.keys()) {
            this.requestBuilder.addHeader(key, headers.get(key));
        }
    }

    private void appendGETParameters() {
        HttpUrl.Builder builder = this.httpUrl.newBuilder();
        for (String key : this.requestConfig.getParameters().keys()) {
            String value = this.requestConfig.getParameters().getAsString(key);
            builder.addQueryParameter(key, value);
        }
        this.requestBuilder.url(builder.build());
    }

    private void appendPOSTParameters() {
        this.multipartBodyBuilder.setType(MultipartBody.FORM);
        for (String key : this.requestConfig.getParameters().keys()) {
            String value = this.requestConfig.getParameters().getAsString(key);
            this.multipartBodyBuilder.addFormDataPart(key, value);
        }
    }
}
