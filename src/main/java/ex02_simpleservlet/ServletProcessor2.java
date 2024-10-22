package ex02_simpleservlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor2 {

    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        URL[] urls = new URL[1];
        File classpath = new File(HttpServer.WEB_ROOT);
        URLStreamHandler urlStreamHandler = null;
        try {
            String repository =
                    new URL("file", null,classpath.getCanonicalPath() +File.separator).toString();
            urls[0] = new URL(null, repository, urlStreamHandler);
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class<?> servletClass = urlClassLoader.loadClass(servletName);

            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);

            Object servlet = servletClass.newInstance();
            ((Servlet)servlet).service(requestFacade, responseFacade);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
