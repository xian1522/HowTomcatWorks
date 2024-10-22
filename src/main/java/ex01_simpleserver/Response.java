package ex01_simpleserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

    private static final int BUFFER_SIZE = 1024;
    private Request request;
    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if(file.exists()) {
            fis = new FileInputStream(file);
            int i = fis.read(bytes, 0 , BUFFER_SIZE);
            while (i != -1) {
                outputStream.write("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n".getBytes());
                outputStream.write(bytes, 0, i);
                i = fis.read(bytes, 0, BUFFER_SIZE);
            }
        }else {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "Content-Length: 23\r\n" + "\r\n"
                    + "<h1>File Not Found</h1>";
            outputStream.write(errorMessage.getBytes());
        }
        if(fis != null) {
            fis.close();
        }
    }
}
