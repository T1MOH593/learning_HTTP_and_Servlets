package com.dmdev.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.google.com"))
                .GET()
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        var request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://www.vk.com"))
                .POST(HttpRequest.BodyPublishers.ofString("Hello, Durov"))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        var response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println();
        System.out.println(response1.headers());

    }
}
