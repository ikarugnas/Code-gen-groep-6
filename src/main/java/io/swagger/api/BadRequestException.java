package io.swagger.api;

public class BadRequestException extends ApiException {
    private int code;
    public BadRequestException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
