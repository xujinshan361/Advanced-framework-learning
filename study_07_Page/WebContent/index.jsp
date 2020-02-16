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
	<table>
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
		</tr>
		<c:forEach items="${pageInfo.list }" var="pi">
			<tr>
				<td>${pi.id }</td>
				<td>${pi.name }</td>
				<td>${pi.age }</td>	
			</tr>
		</c:forEach>
	</table>
	<a href="page?pageNumber=${pageInfo.pageNumber-1 }&pageSize=${pageInfo.pageSize }" <c:if test="${pageInfo.pageNumber<=1 }"> onclick="javascript:return false;"</c:if>>上一页</a>
	<a href="page?pageNumber=${pageInfo.pageNumber+1 }&pageSize=${pageInfo.pageSize }" <c:if test="${pageInfo.pageNumber>=pageInfo.total }"> onclick="javascript:return false;"</c:if>>下一页</a>
</body>
</html>