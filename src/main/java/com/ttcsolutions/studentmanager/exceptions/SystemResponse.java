package com.ttcsolutions.studentmanager.exceptions;

public class SystemResponse<T> {
    private int status;
    private String message;
    private T data;

    public SystemResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public SystemResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
