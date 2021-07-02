package com.dmdev.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketRunner {

    public static void main(String[] args) throws IOException {
        try (var serverSocket = new ServerSocket(7777);
             var socket = serverSocket.accept();
             var dataOutputStream = new DataOutputStream(socket.getOutputStream());
             var dataInputStream = new DataInputStream(socket.getInputStream())) {
            var request = dataInputStream.readUTF();
            dataOutputStream.writeUTF("Server got '" + request + "'");
            while(!"stop".equals(request)) {
                request = dataInputStream.readUTF();
                dataOutputStream.writeUTF("Server got '" + request + "'");
                System.out.println("client request: '" + request + "'");
            }
        }
    }
}
