package com.luxoft.requestutils;
import com.luxoft.exceptions.ServerExceptions;
import com.luxoft.domain.Request;
import com.luxoft.responceutils.ResourceReader;
import com.luxoft.responceutils.ResponseWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
public class RequestHandler {
    BufferedReader socketReader;
    BufferedWriter socketWriter;
    String webappPath;
    public RequestHandler(BufferedReader socketReader, BufferedWriter socketWriter, String webappPath) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
        this.webappPath = webappPath;
    }
    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser(socketWriter, socketReader);
        ResourceReader reader = new ResourceReader(socketWriter);
        ResponseWriter writer = new ResponseWriter(socketWriter);
        try {
            Request request = requestParser.parse();
            InputStream inputStream = reader.readResource(request.getUri(), webappPath);
            writer.writeSuccessResponseToClient(inputStream);
        } catch (ServerExceptions e) {
            writer.writeError(e.getStatusCode());
            throw e;
        }
    }
}