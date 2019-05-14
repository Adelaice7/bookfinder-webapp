package com.bookfinderapi.bookfinderwebapp;

public class MainController {
    public class MainController extends HttpServlet {
        
        public void doServiceGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            
            response.setContentType("text/html");
            
            PrintWriter out = response.getWriter();
            
            out.println("<html><body>");
            out.println("<h1>Book Finder Servlet</h1>");
            out.println("</body></html>");
        }
    }

}
