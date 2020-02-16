package com.xujinshan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.People;
import com.xujinshan.service.PeopleService;
import com.xujinshan.service.impl.PeopleServiceImpl;

// 大部分注解都有默认属性，如果注解中只给默认属性赋值，可以省略属性值
// 否则在注解的(属性名=属性值)格式
// 如果一个属性是数组类型格式：属性名={值，值....},如果该数组只有一个值，可以省略大括号
// 如果类不是基本数据类型或者String，而是一个类类型，语法 ：属性名=@类型
// 注解中@ 表示引用注解声明
@WebServlet("/show")
//@WebServlet(value = {"/show"})
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PeopleService peopleService = new PeopleServiceImpl();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<People> list = peopleService.show();
		request.setAttribute("list", list);
		// 相对路径
		// 只要路径中以/ 开头的都叫全路径，从项目根目录(WebContent)出发找其他的资源的过程
		// 只要不以 / 开头都是相对路径，相对路径是从当前资源出发招到其他资源的过程
		// 如果请求转发 		/   表示WebContent目录
		// 如果是重定向，静态资源引用，<img src=""/> <a href=""/> <script src=""/> css引用时，其中/ 都表示的是Tomcat的webapps文件夹根目录
		// 服务器根目录
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
