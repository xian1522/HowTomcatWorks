package ex02_simpleservlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "target" + File.separator +"classes";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 httpServer = new HttpServer2();
        httpServer.await();
    }

    public void await() {
        try {
            ServerSocket serverSocket
                    = new ServerSocket(8080, 1, InetAddress.getByName("localhost"));
            while (!shutdown) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();

                Request request = new Request(inputStream);
                request.parse();

                OutputStream outputStream = socket.getOutputStream();
                Response response = new Response(outputStream);
                response.setRequest(request);

                if(request.getUri().startsWith("/servlet/")) {
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request, response);
                }else {
                    StaticServletProcessor staticProcessor = new StaticServletProcessor();
                    staticProcessor.process(request, response);
                }

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
