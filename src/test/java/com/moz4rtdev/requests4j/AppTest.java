package com.moz4rtdev.requests4j;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.moz4rtdev.requests4j.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testGetRequest() throws IOException, InterruptedException {
        Response response = Request.get(
            "https://httpbin.org/status/200"
        ).send();
        assertTrue(response.statusCode() == 200);
    }

    @Test
    public void testPostRequest() throws IOException, InterruptedException {
        Response response = Request.post("https://httpbin.org/status/201")
            .body("hello world")
            .send();
        assertTrue(response.statusCode() == 201);
    }

    @Test
    public void testPutRequest() throws IOException, InterruptedException {
        Response response = Request.put("https://httpbin.org/status/201")
            .body("hello world")
            .send();
        assertTrue(response.statusCode() == 201);
    }

    @Test
    public void testPatchRequest() throws IOException, InterruptedException {
        Response response = Request.patch("https://httpbin.org/status/201")
            .body("hello world")
            .send();
        assertTrue(response.statusCode() == 201);
    }

    @Test
    public void testDeleteRequest() throws IOException, InterruptedException {
        Response response = Request.delete(
            "https://httpbin.org/status/201"
        ).send();
        assertTrue(response.statusCode() == 201);
    }
}
