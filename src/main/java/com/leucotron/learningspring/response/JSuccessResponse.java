package com.leucotron.learningspring.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.swagger.annotations.ApiModel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author flavio
 */
@ApiModel(value = "Response")
public class JSuccessResponse {

    private final Boolean success = Boolean.TRUE;
    private final String message;    
    private final Map<String, Object> data = new HashMap<>();

    public JSuccessResponse(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(String property, Object value) {
        data.put(property, value);
    }

}
