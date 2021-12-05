package com.luxoft.domain;
public enum StatusCode {
    BAD_REQUEST(400, " Bad Request\r\n"),
    NOT_FOUND(404, " Not Found \r\n"),
    METHOD_NOT_ALLOWED(405, " Method not allowed\r\n"),
    INTERNAL_SERVER_ERROR(500, " Internal Server Error \r\n");
    private final int code;
    private final String status;
    StatusCode(int code, String statusText) {
        this.code = code;
        this.status = statusText;
    }
    public int getCode() {
        return code;
    }
    public String getStatus() {
        return status;
    }
}