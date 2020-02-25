<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
</head>
<body>
	<form action="demo08" method="post">
		<input type="text" name="name"/>
		<input type="text" name="age">
		<input type="checkbox" name="hover" value="学习"/>
		<input type="checkbox" name="hover" value="写代码"/>
		<input type="checkbox" name="hover" value="看视频"/>
		<input type="checkbox" name="hover" value="看书"/>
		
		<input type="text" name="peo.name">
		<input type="text" name="peo.age">
		
		<input type="text" name="peo[0].name">
		<input type="text" name="peo[0].age">
		<input type="text" name="peo[1].name">
		<input type="text" name="peo[1].age">
		<input type="submit" name="提交">
		<a href="demo08?id=123&name=abc">跳转</a>
		<a href="demo09/123/abc">跳转</a>
	</form>
</body>
</html>
