<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="transfer" method="post">
转账账号：<input type="text" name="accountOutNo"/><br/>
密码：<input type="password" name="accountOutPassword"/><br/>
金额：<input type="text" name="accountOutBalance"/><br/>
收款账户：<input type="text" name="accountInNo"/><br/>
收款姓名：<input type="text" name="accountInName"/><br/>
<input type="submit" value="转账"/>
</form>
</body>
</html>