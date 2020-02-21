<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(function() {
	$("a").click(function(){
		// 浏览器带有缓存功能，不会多次请求相同数据
		// 传入date参数，解决浏览器缓存问题
		$("img").attr("src","validCode?date="+newDate())
		return false;
	})
})

</script>
</head>
<body>
${error }
<!--  <img alt="" src="img/a.png">  -->

<form action="login" method="post">
	用户名：<input type="text" name="username" /><br />
	密码：<input type="password" name="password" /><br />
	验证码：<input type="text" size="1" name="code" /><img alt="" src="validCode" width="80" heigth="40"/><a href="">看不清</a><br />
	<input type="submit" value="登录" /><input type="reset" value="重置">
</form>
</body>
</html>
