package com.leucotron.learningspring.utils;

import com.google.gson.JsonObject;

public class ResponseGenerator {
    
    private final JsonObject response;
    
    public ResponseGenerator() {
        response = new JsonObject();
        response.addProperty("success", Boolean.TRUE);
    }
    
    public String setMessage(String message) {
        response.addProperty("message", message);
        return response.toString();
    }
}
