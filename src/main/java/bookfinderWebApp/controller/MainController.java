package bookfinderWebApp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookfinderWebApp.model.Book;
import bookfinderWebApp.utils.BooksUtils;

@WebServlet("/books")
public class MainController extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Setting up the content type of webpage
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>Bookfinder API</h1>");

        String query = "Lord";

        List<Book> booksList = BooksUtils.fetchData(query);
        
        System.out.println(booksList.size());

        request.setAttribute("booksList", booksList);
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }

}
