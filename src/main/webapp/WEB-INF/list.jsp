<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Shoppinglist</title>
	<link rel="stylesheet" href="/styles/demo.css">
<title>Shopping list</title>
</head>
<body>
<h1>List of Items to shop</h1>

    <c:forEach items="${items}" var="shoppingListTtem" >
    <li><c:out value = "${shoppingListTtem.title}"/><input type = "button" value = "Removeitem"></li>
	</c:forEach>
	<form class ="addtoitems" method="post">
    <input name="title" type="text" required placeholder="type item here..." autofocus /> 
    <input type="submit" value="Addtolist" /></form> <br>

</body>
</html>