package com.waltonbd.assignment.springngblog.dto;

public class BaseResponse {
    public int status;
    public String message;

    public BaseResponse() {
    }

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
