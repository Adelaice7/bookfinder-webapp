package bookfinderWebApp.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/error")
public class ErrorHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String title = "Error information";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType + "<html>\n" +
                "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>"
                + title + "</title></head>\n" + "<body>");

        if (throwable == null && statusCode == null) {
            out.println("<h3>Error information missing</h3>");
        } else {
            out.write("<h2>Error " + statusCode + "</h2>");

            out.println("<h3>Exception Details</h3>");
            out.println("<ul><li><strong>Servlet Name</strong>= " + servletName + "</li>");
            out.println("<li><strong>Exception Name</strong>= " + throwable.getClass().getName() + "</li>");
            out.println("<li><strong>Requested URI</strong>= " + requestUri + "</li>");
            out.println("<li><strong>Exception Message</strong>= " + throwable.getMessage() + "</li></ul>");
        }
        
        out.println("Back to <a href='/bookfinder'>home page</a>");

        out.println("</body>\n</html>");
        out.close();

    }
}
