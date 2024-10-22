package ex02_simpleservlet;

import java.io.IOException;

public class StaticServletProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
