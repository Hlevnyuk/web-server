package com.luxoft.domain;
public class Request {
    String uri;
    HttpMethod method;
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public void setMethod(HttpMethod method) {
        this.method = method;
    }
}