package com.baghdadit.simplehttp.config;

public class RequestConfig {
    private HttpActionType actionType;
    private String url;
    private RequestParameters parameters;
    private RequestHeaders headers;
    private RequestAttachments attachments;

    public void setActionType(HttpActionType actionType) {
        this.actionType = actionType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setParameters(RequestParameters parameters) {
        this.parameters = parameters;
    }

    public void setHeaders(RequestHeaders headers) {
        this.headers = headers;
    }

    public void setAttachments(RequestAttachments attachments) {
        this.attachments = attachments;
    }

    public HttpActionType getActionType() {
        return actionType;
    }

    public String getUrl() {
        return url;
    }

    public RequestParameters getParameters() {
        return parameters;
    }

    public RequestHeaders getHeaders() {
        return headers;
    }

    public RequestAttachments getAttachments() {
        return attachments;
    }
}
