package ex02_simpleservlet;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderTest {

    @Test
    public void urlClassLoader() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        URL[] urls = new URL[1];
        File classpath = new File(HttpServer.WEB_ROOT + File.separator);


        File file = new File(classpath.getCanonicalPath() + File.separator);
        URI uri = file.toURI();
        URL url = uri.toURL();
        urls[0] = url;

        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class<?> servletClass = urlClassLoader.loadClass("SimpleServlet");
        Object object = servletClass.newInstance();
    }
}
