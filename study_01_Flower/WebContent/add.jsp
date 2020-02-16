<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
//页面加载完成后执行
//相当于: window.onload=function(){}   $(document).ready(function(){});
$(function(){
	$("form").submit(function(){
		//表单选择器,    :input标签type属性值
		if($(":text:eq(0)").val()==""||$(":text:eq(1)").val()==""||$(":text:eq(2)").val()==""){
			alert("请填写完整信息");
			//阻止默认行为
			return false;
		}
	});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
</head>
<body>
	<form method="post" action="insert">
		<table border="1">
			<tr>
				<td colspan="2" style="test-align:center;font-size:30px;font-weight:bold;">花卉信息</td>
			</tr>
			<tr>
				<td><b>花卉名称：</b></td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td><b>花卉价格：</b></td>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td><b>原产地</b></td>
				<td><input type="text" name="production" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="提交"/>
					<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
