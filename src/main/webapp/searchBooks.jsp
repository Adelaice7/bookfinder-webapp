<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SearchBooks</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="script.js" type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<div class="simple-formbox">

			<%
			    String errorMessage = (String) request.getAttribute("errorMessage");
			%>
			<c:if test="${not empty errorMessage}">
				<%
				    out.println("<div class=\"errorBox\">");
				        out.println(errorMessage);
				        out.println("</div>");
				%>
			</c:if>
			
			<h3>Simple Search</h3>
			<form id="searchBooks" name="searchBooks" method="POST"
				action="searchBooks">
				<input type="hidden" name="searchType" value="simpleSearch"/>
				<input type="text" name="simpleBookQuery"
					placeholder="Search books..." /> <br />

				<button type="submit" name="submitSimpleSearch" class="btn">Submit</button>
			</form>
			<br /> <a href="#" id="detailedSearchLink">Detailed Search</a>

			<div id="detailedSearchContainer" style="display: none;">
				<h3>Detailed Search</h3>
				<form id="detailedSearchBooks" name="detailedSearchBooks"
					method="POST" action="searchBooks">
					<input type="hidden" name="searchType" value="detailedSearch"/>
					<input type="text" name="title" placeholder="Title..." /><br /> <input
						type="text" name="authors" placeholder="Authors..." /><br /> <input
						type="text" name="publisher" placeholder="Publisher..." /><br />
					<input type="text" name="isbn" placeholder="ISBN..." /><br />
					<button type="submit" name="submitDetailedSearch" class="btn">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>