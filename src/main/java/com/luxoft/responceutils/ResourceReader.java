package com.luxoft.responceutils;
import java.io.*;
import static com.luxoft.domain.StatusCode.NOT_FOUND;
public class ResourceReader {
    private BufferedWriter writer;
    ResponseWriter responseWriter = new ResponseWriter(writer);
    public ResourceReader(BufferedWriter writer) {
        this.writer = writer;
    }
    public InputStream readResource(String uri, String path) throws IOException {
        File resource = new File(path, uri);
        if (!resource.exists()) {
            responseWriter.writeError(NOT_FOUND);
        }
        return new FileInputStream(resource);
    }
}