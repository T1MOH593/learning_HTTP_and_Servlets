package com.dmdev.http.socket;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DatagramRunner {

    public static void main(String[] args) throws IOException {
        try (var socket = new DatagramSocket()) {
            byte[] buffer = "Hello from UDP client!".getBytes(StandardCharsets.US_ASCII);
            InetAddress inetAddress = Inet4Address.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, 7777);
            socket.send(packet);
        }
    }
}
