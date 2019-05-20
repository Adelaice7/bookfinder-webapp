<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bookfinder App</title>
</head>
<body>
<a href="books">Get Books, Fixed Query</a><br/>
<a href="searchBooks">Search for Books</a><br/>
<p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
</body>
</html> 