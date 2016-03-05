package com.ostdlabs.bank_account.web.vo;

import java.util.List;

public class OperationStatus {
    private String message;
    private boolean hasErrors;
    private List<String> errors;

    public OperationStatus(String message, boolean hasErrors, List<String> errors) {
        this.message = message;
        this.hasErrors = hasErrors;
        this.errors = errors;
    }

    public static OperationStatus newInstance(String message, boolean hasErrors, List<String> errors) {
        return new OperationStatus(message, hasErrors, errors);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
