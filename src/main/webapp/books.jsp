<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List, bookfinderWebApp.model.Book"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get books</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
	<div id="wrapper">
		<div class="result-box">
			<table class="bookResults">
				<tr>
					<th>Title</th>
					<th>Published</th>
					<th>Authors</th>
                    <th>Publisher</th>
					<th>Price</th>
				</tr>
				<%
				    List<Book> booksList = (List<Book>) request.getAttribute("booksList");
				%>

				<c:forEach items="${booksList}" var="book">
					<tr>
						<td><c:out value="${book.title}" /> <c:if
								test="!${book.subtitle}.isEmpty()">
				    -
				    <c:out value="${book.subtitle}" />
							</c:if></td>

						<td><c:out value="${book.publishedDate}" /></td>
						<td><c:out value="${book.authors}" /></td>
                        <td><c:out value="${book.publisher}" /></td>
						<td><c:out value="${book.formattedListPrice}" /></td>
					</tr>
				</c:forEach>


			</table>
		</div>
		<br /> <a href="index.jsp">Back to main page</a>
	</div>
</body>
</html>