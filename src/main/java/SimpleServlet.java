import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleServlet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println(" from service");
        PrintWriter writer = servletResponse.getWriter();
        writer.write("HTTP/1.0 200 OK\r\nContent-Type: text/html\r\n\r\n");
        writer.println("Hello Servlet");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("servlet destory");
    }
}
