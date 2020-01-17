package com.leucotron.learningspring.response;

/**
 *
 * @author flavio
 * @param <T>
 */
public class JSuccessResponse<T> {

    private final Boolean success = Boolean.TRUE;
    private final String message;
    private final T data;

    public JSuccessResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
