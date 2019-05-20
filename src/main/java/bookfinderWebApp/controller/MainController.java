package bookfinderWebApp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bookfinderWebApp.model.Book;
import bookfinderWebApp.utils.BooksUtils;

@WebServlet("/books")
public class MainController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Setting up the content type of webpage
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<h1>Bookfinder API</h1>");

        String query = "Lord";

        List<Book> booksList = BooksUtils.fetchData(query);

        if (booksList == null) {
            logger.error("Books list empty. ServletException with code 500.");
            throw new ServletException("Books list returned empty.");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        request.setAttribute("booksList", booksList);
        request.getRequestDispatcher("books.jsp").forward(request, response);
        out.close();
    }

}
