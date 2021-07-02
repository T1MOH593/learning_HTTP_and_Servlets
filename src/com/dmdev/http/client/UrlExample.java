package com.dmdev.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {

    public static void main(String[] args) throws IOException {
        var url = new URL("file:C:\\Users\\User\\IdeaProjects\\http-servlets\\src\\com\\dmdev\\http\\socket\\SocketRunner.java");
        var urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);
        try (var inputStream = urlConnection.getInputStream()) {
            System.out.println(new String(inputStream.readAllBytes()));
        }
    }

    private static void checkGoogle() throws IOException {
        var url = new URL("https://www.google.com");
        var urlConnection = url.openConnection();

        try (var inputStream = urlConnection.getInputStream()) {
            var bytes = inputStream.readAllBytes();
            System.out.println(new String(bytes));
        }
    }
}
