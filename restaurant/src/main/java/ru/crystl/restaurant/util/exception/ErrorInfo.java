package ru.crystl.restaurant.util.exception;

import javax.lang.model.type.ErrorType;

public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String[] details;

    public ErrorInfo(CharSequence url, ErrorType type, String... details) {
        this.url = url.toString();
        this.type = type;
        this.details = details;
    }
}
