package com.xujinshan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xujinshan.pojo.Users;

/**
 * Ajax
 * 		标准请求响应时浏览器的动作(同步操作)
 * 			浏览器请求声明资源，跟随显示声明资源
 * 		Ajax：异步请求
 * 			局部刷新，通过异步请求，请求到服务器资源数据后，通过脚本修改页面中部分内容
 * 		Ajax由JavaScript推出
 * 			由jQuery 对js中Ajax代码进行的封装，达到使用方便的效果
 * 		jQuery中Ajax分层
 * 			第一层 $.ajax({ 属性名:值,属性名:值})
 * 				是 jquery 中功能最全的.代码写起来相对最麻烦的.
 * 			第二层(简化$.ajax)
 * 				$.get(url,data,success,dataType))
 * 				$.post(url,data,success,dataType)
 * 			第三层(简化$.get())
 * 				$.getJSON(url,data,success). 相 当 于 设 置 $.get 中dataType=”json”
 * 				$.getScript(url,data,success) 相 当 于 设 置 $.get 中 dataType=”script”
 * 		如果服务器返回数据是从表中取出.为了方便客户端操作返回的数 据,服务器端返回的数据设置成 json
 * 			客户端把 json 当作对象或数组操作
 * 		json:数据格式
 * 			JsonObject : json 对象,理解成 java 中对象
 * 				{“key”:value,”key”:value}
 * 			JsonArray:json 数组
 * 				[{“key”:value,”key”:value},{}]
 * @author xujinshan361@163.com
 *
 */
@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("执行控制器");
		String name = req.getParameter("name");
		Users users = new Users();
		users.setId(1);
		users.setPassword("123");
		users.setUsername("张三");
		Users users1 = new Users();
		users1.setId(2);
		users1.setPassword("22");
		users1.setUsername("李四");
		
		List<Users> list = new ArrayList<Users>();
		list.add(users1);
		list.add(users);
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(list);
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

}
