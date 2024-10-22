package ex01_simpleserver;

import org.junit.jupiter.api.Test;

import java.io.*;

public class FileTest {

    @Test
    public void sendFile() throws IOException {
        File file = new File(HttpServer.WEB_ROOT + "/index.html");
        byte[] bytes = new byte[1024];

        FileInputStream fis = new FileInputStream(file);
//        fis.read(bytes);

        int ch = fis.read(bytes, 0 , 10);
        while (ch != -1) {
//            outputStream.write(bytes, 0, i);
            System.out.println("ch: " + ch);
            ch = fis.read(bytes, 0, 10);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) {
            sb.append((char)bytes[i]);
        }
        System.out.println(sb);
    }
}
