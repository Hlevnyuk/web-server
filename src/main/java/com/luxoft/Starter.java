package com.luxoft;
import com.luxoft.server.Server;
import java.io.IOException;
public class Starter {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(5000);
        server.setWebappPath("./src/main/resources/");
        server.start();
    }
}