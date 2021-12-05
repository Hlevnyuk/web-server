package com.luxoft.requestutils;
import com.luxoft.domain.HttpMethod;
import com.luxoft.domain.Request;
import com.luxoft.responceutils.ResponseWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;
import static com.luxoft.domain.StatusCode.*;
public class RequestParser {
    private final BufferedReader reader;
    private final BufferedWriter writer;
    RequestParser(BufferedWriter writer, BufferedReader reader) {
        this.reader = reader;
        this.writer = writer;
    }
    Request parse() throws IOException {
        Request request = new Request();
        String line = reader.readLine();
        injectUriAndMethod(line, request);
        return request;
    }
    void injectUriAndMethod(String line, Request request) throws IOException {
        ResponseWriter responseWriter = new ResponseWriter(writer);
        if (Objects.equals(line, null)) {
            responseWriter.writeError(BAD_REQUEST);
        }
        String[] words = line.split(" ");
        if (words[0].equals("GET")) {
            request.setMethod(HttpMethod.GET);
        } else {
            request.setMethod(HttpMethod.POST);
            responseWriter.writeError(METHOD_NOT_ALLOWED);
        }
        request.setUri(words[1]);
    }
}