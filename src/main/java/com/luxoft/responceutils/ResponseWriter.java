package com.luxoft.responceutils;
import com.luxoft.domain.StatusCode;
import com.luxoft.exceptions.ServerExceptions;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static com.luxoft.domain.StatusCode.*;
public class ResponseWriter {
    private final BufferedWriter writer;
    public ResponseWriter(BufferedWriter writer) {
        this.writer = writer;
    }
    public void writeSuccessResponseToClient(InputStream inputStream) throws IOException {
        try {
            writer.write("HTTP/1.1 200 OK");
            writer.newLine();
            writer.newLine();
            byte[] buffer = new byte[4800];
            while ((inputStream.read(buffer)) > 0) {
                String content = new String(buffer, StandardCharsets.UTF_8);
                writer.write(content);
            }
        } catch (ServerExceptions e) {
            writeError(e.getStatusCode());
        }
    }
    public void writeError(StatusCode statusCode) throws IOException {
        switch (statusCode) {
            case NOT_FOUND -> writer.write("HTTP/1.1" + NOT_FOUND.getCode() + NOT_FOUND.getStatus());
            case BAD_REQUEST -> writer.write("HTTP/1.1" + BAD_REQUEST.getCode() + BAD_REQUEST.getStatus());
            case METHOD_NOT_ALLOWED -> writer.write("HTTP/1.1" + METHOD_NOT_ALLOWED.getCode() + METHOD_NOT_ALLOWED.getStatus());
            case INTERNAL_SERVER_ERROR -> writer.write("HTTP/1.1" + INTERNAL_SERVER_ERROR.getCode() + INTERNAL_SERVER_ERROR.getStatus());
        }
    }
}