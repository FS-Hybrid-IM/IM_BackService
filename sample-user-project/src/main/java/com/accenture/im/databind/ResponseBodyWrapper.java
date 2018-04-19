package com.accenture.im.databind;

public class ResponseBodyWrapper {
    private String status;
    private Object result;
    private ErrorResponse errors;

    public ResponseBodyWrapper(String status, Object result, ErrorResponse errors) {
        super();
        this.status = status;
        this.result = result;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ErrorResponse getErrors() {
        return errors;
    }

    public void setErrors(ErrorResponse errors) {
        this.errors = errors;
    }
}
