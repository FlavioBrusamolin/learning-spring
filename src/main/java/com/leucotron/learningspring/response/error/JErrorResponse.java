package com.leucotron.learningspring.response.error;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author flavio
 */
public class JErrorResponse {

    private final Boolean success = Boolean.FALSE;
    private final List<JError> errors = new ArrayList<>();

    public Boolean getSuccess() {
        return success;
    }

    public List<JError> getErrors() {
        return errors;
    }

    public void setErrors(List<JError> errors) {
        this.errors.addAll(errors);
    }

    public void addError(JError error) {
        this.errors.add(error);
    }

}
