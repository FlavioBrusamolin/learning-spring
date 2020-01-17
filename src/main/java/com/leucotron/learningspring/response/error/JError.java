package com.leucotron.learningspring.response.error;

/**
 *
 * @author flavio
 */
public class JError {

    private final String title;
    private final String detail;

    public JError(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
