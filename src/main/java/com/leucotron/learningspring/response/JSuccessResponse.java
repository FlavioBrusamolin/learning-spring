package com.leucotron.learningspring.response;

import io.swagger.annotations.ApiModel;

/**
 *
 * @author flavio
 * @param <T>
 */
@ApiModel(value = "Response")
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
