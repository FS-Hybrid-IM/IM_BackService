package com.accenture.im.databind;

import java.util.List;

public class ErrorResponse {
    private List<FieldError> fields;
    private List<GlobalError> global;

    public List<FieldError> getFields() {
        return fields;
    }

    public void setFields(List<FieldError> fields) {
        this.fields = fields;
    }

    public List<GlobalError> getGlobal() {
        return global;
    }

    public void setGlobal(List<GlobalError> global) {
        this.global = global;
    }

    public static class FieldError {
        String field;
        String message;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class GlobalError {
        String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
