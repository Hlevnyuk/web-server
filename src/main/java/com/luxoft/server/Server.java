package com.luxoft.server;
import com.luxoft.requestutils.RequestHandler;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    int port = 3000;
    String webappPath;
    public void setPort(int port) {
        this.port = port;
    }
    public void setWebappPath(String webappPath) {
        this.webappPath = webappPath;
    }
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    RequestHandler requestHandler = new RequestHandler(socketReader, socketWriter, webappPath);
                    requestHandler.handle();
                }
            }
        }
    }
}