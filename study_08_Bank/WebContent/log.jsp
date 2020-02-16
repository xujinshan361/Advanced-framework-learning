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
		<td>转账账号</td>
		<td>收款账号</td>
		<td>收款金额</td>
	</tr>
	<c:forEach items="${pageInfo.list }" var="pageInfo">
		<tr>
		<td>${pageInfo.accountOut }</td>
		<td>${pageInfo.accountIn }</td>
		<td>${pageInfo.money }</td>
	</tr>
	</c:forEach>
</table>
<a href="show?pageNumber=${pageInfo.pageNumber-1 }&pageSize=${pageInfo.pageSize }" <c:if test="${pageInfo.pageNumber<=1 }"> onclick="javascript:return false;"</c:if>>上一页</a>
<a href="showpageNumber=${pageInfo.pageNumber+1 }&pageSize=${pageInfo.pageSize }" <c:if test="${pageInfo.pageNumber>=pageInfo.total }"> onclick="javascript:return false;"</c:if>>下一页</a>
</body>
</html>