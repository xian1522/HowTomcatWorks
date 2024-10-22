package ex01_simpleserver;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public String getUri() {
        return uri;
    }

    public void parse() {
        byte[] bytes = new byte[1024];
        StringBuffer request = new StringBuffer();
        try {
            int read = inputStream.read(bytes);
            for(int i = 0; i < read; i++) {
                request.append((char)bytes[i]);
            }
            uri = parseUri(request.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String parseUri(String uri) {
        int start = uri.indexOf(" ");
        if(start != -1) {
            int end = uri.indexOf(" ", start + 1);
            uri = uri.substring(start + 1, end);
            return uri;
        }
        return null;
    }
}
