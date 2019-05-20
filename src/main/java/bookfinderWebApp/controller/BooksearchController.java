package bookfinderWebApp.controller;

import java.io.IOException;
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

@WebServlet("/searchBooks")
public class BooksearchController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BooksearchController.class);

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("searchBooks.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String searchType = request.getParameter("searchType");
        logger.info("Search type : " + searchType);

        switch (searchType) {

            case "simpleSearch":
                String simpleQuery = request.getParameter("simpleBookQuery");

                if (!simpleQuery.isEmpty()) {
                    logger.info(simpleQuery);

                    List<Book> booksList = BooksUtils.fetchData(simpleQuery);

                    if (booksList == null) {
                        logger.error("****************************************\n");
                        logger.error("Books list empty. ServletException with code 500.");
                        logger.error("****************************************\n");
                        throw new ServletException("Books list returned empty.");
                    } else {
                        response.setStatus(HttpServletResponse.SC_OK);
                    }

                    request.setAttribute("booksList", booksList);
                    request.getRequestDispatcher("books.jsp").forward(request, response);
                } else {
                    String errorMessage = "Search terms cannot be empty!";
                    logger.error("Empty query string given.");
                    logger.info("Error message: " + errorMessage);
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("searchBooks.jsp").forward(request, response);
                }
                break;

            case "detailedSearch":
                String title = request.getParameter("title");
                String authors = request.getParameter("authors");
                String publisher = request.getParameter("publisher");
                String isbn = request.getParameter("isbn");

                StringBuilder builder = new StringBuilder();
                
                builder.append(title);
                if (!authors.isEmpty()) {
                    builder.append("+inauthor:");
                    builder.append(authors);
                }
                if (!publisher.isEmpty()) {
                    builder.append("+inpublisher:");
                    builder.append(publisher);
                }
                if (!isbn.isEmpty()) {
                    builder.append("+isbn:");
                    builder.append(isbn);
                }
                String detailedQuery = builder.toString();
                
                logger.info("Query: \n" + detailedQuery );

                List<Book> booksList = BooksUtils.fetchData(detailedQuery);
                if (booksList == null) {
                    logger.error("****************************************\n");
                    logger.error("Books list empty. ServletException with code 500.");
                    logger.error("****************************************\n");
                    throw new ServletException("Books list returned empty.");
                } else {
                    response.setStatus(HttpServletResponse.SC_OK);
                }

                request.setAttribute("booksList", booksList);
                request.getRequestDispatcher("books.jsp").forward(request, response);
                
                break;

        }

    }

}
