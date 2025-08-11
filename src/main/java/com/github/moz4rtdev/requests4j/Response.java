package com.github.moz4rtdev.requests4j;

import java.util.List;
import java.util.Map;

public class Response {

    private int statusCode;
    private byte[] rawBody;
    private Map<String, List<String>> headers;

    public Response(
        byte[] rawBody,
        int statusCode,
        Map<String, List<String>> headers
    ) {
        this.rawBody = rawBody;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    /**
     * Returns the raw body of the response as a byte array.
     */
    public byte[] rawBody() {
        return rawBody;
    }

    /**
     * Returns the body of the response as a string.
     */
    public String body() {
        return new String(rawBody);
    }

    /**
     * Returns the status code of the response.
     */
    public int statusCode() {
        return statusCode;
    }

    /**
     * Returns the headers of the response.
     */
    public Map<String, List<String>> headers() {
        return headers;
    }
}
