package com.moz4rtdev.requests4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private HttpClient client = HttpClient.newHttpClient();
    private Builder requestBuilder = HttpRequest.newBuilder();
    private Map<String, String> headers = new HashMap<>();
    private ProxySet proxySet = new ProxySet();
    private String body;
    private String method;
    private String url;

    private Request(String method, String url) {
        this.method = method;
        this.url = url;
    }

    /**
     * Creates a new GET request with the specified URL.
     */
    public static Request get(String url) {
        return new Request("GET", url);
    }

    /**
     * Creates a new POST request with the specified URL.
     */
    public static Request post(String url) {
        return new Request("POST", url);
    }

    /**
     * Creates a new PUT request with the specified URL.
     */
    public static Request put(String url) {
        return new Request("PUT", url);
    }

    /**
     * Creates a new DELETE request with the specified URL.
     */
    public static Request delete(String url) {
        return new Request("DELETE", url);
    }

    /**
     * Creates a new PATCH request with the specified URL.
     */
    public static Request patch(String url) {
        return new Request("PATCH", url);
    }

    /**
     * Adds a header to the request.
     */
    public Request header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    /**
     * Adds multiple headers to the request.
     */
    public Request headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    private void setProxy(String proxyTxt) {
        proxySet.setProxy(proxyTxt);
        Proxy proxy = proxySet.getProxy();
        ProxySelector proxySelector = ProxySelector.of(
            (InetSocketAddress) proxy.address()
        );
        if (proxySet.getProxyAuth() != null) {
            this.client = HttpClient.newBuilder()
                .proxy(proxySelector)
                .authenticator(proxySet.getProxyAuth())
                .build();
        } else {
            this.client = HttpClient.newBuilder().proxy(proxySelector).build();
        }
    }

    /**
     * Sets the proxy for the request.
     */
    public Request proxy(String proxyTxt) {
        this.setProxy(proxyTxt);
        return this;
    }

    /**
     * Sets the body for the request.
     */
    public Request body(String body) {
        this.body = body;
        return this;
    }

    /**
     * Sends the request and returns the response.
     */
    public Response send() throws IOException, InterruptedException {
        requestBuilder.uri(URI.create(this.url));
        if (headers != null) {
            for (String header : headers.keySet()) {
                this.requestBuilder.header(header, headers.get(header));
            }
        }
        HttpRequest request;
        switch (this.method) {
            case "GET":
                request = this.requestBuilder.build();
                break;
            case "POST":
                request = this.requestBuilder.POST(
                    HttpRequest.BodyPublishers.ofString(body)
                ).build();
                break;
            case "PUT":
                request = this.requestBuilder.PUT(
                    HttpRequest.BodyPublishers.ofString(body)
                ).build();
                break;
            case "PATCH":
                request = this.requestBuilder.PUT(
                    HttpRequest.BodyPublishers.ofString(body)
                ).build();
                break;
            case "DELETE":
                request = this.requestBuilder.DELETE().build();
                break;
            default:
                throw new IllegalArgumentException("Invalid method");
        }
        HttpResponse<byte[]> responseHttp = this.client.send(
            request,
            HttpResponse.BodyHandlers.ofByteArray()
        );

        return new Response(
            responseHttp.body(),
            responseHttp.statusCode(),
            responseHttp.headers().map()
        );
    }
}
