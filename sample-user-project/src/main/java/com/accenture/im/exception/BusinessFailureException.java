package com.accenture.im.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class BusinessFailureException extends RuntimeException {
    private final transient Errors errors;
    private final transient List<MessageSourceResolvable> resolvableList;
    private final transient Object response;

    public BusinessFailureException() {
        super();
        this.errors = null;
        this.resolvableList = null;
        this.response = null;
    }

    public BusinessFailureException(String message) {
        super(message);
        this.errors = null;
        this.resolvableList = null;
        this.response = null;
    }

    public BusinessFailureException(Throwable cause) {
        super(cause);
        this.errors = null;
        this.resolvableList = null;
        this.response = null;
    }

    public BusinessFailureException(String message, Throwable cause) {
        super(message, cause);
        this.errors = null;
        this.resolvableList = null;
        this.response = null;
    }

    public BusinessFailureException(Errors errors) {
        super();
        this.errors = errors;
        this.resolvableList = null;
        this.response = null;
    }

    public BusinessFailureException(MessageSourceResolvable resolvable) {
        super();
        ArrayList<MessageSourceResolvable> list = new ArrayList<>();
        list.add(resolvable);
        this.resolvableList = list;
        this.errors = null;
        this.response = null;
    }

    public BusinessFailureException(MessageSourceResolvable resolvable, Object response) {
        super();
        ArrayList<MessageSourceResolvable> list = new ArrayList<>();
        list.add(resolvable);
        this.resolvableList = list;
        this.errors = null;
        this.response = response;
    }

    public BusinessFailureException(List<MessageSourceResolvable> resolvableList) {
        super();
        this.resolvableList = resolvableList;
        this.errors = null;
        this.response = null;
    }

    public BusinessFailureException(List<MessageSourceResolvable> resolvableList, Object response) {
        super();
        this.resolvableList = resolvableList;
        this.errors = null;
        this.response = response;
    }

    public Errors getErrors() {
        return errors;
    }

    public List<MessageSourceResolvable> getResolvableList() {
        return resolvableList;
    }

    public Object getResponse() {
        return response;
    }
}
