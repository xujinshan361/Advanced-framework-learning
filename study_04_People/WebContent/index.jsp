<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td>编号</td>
		<td>姓名</td>
		<td>年龄</td>
	</tr>
	<c:forEach items="${list }" var="people">
		<tr>
		<td>${people.id }</td>
		<td>${people.name }</td>
		<td>${people.age }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>