package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xujinshan.pojo.Users;
import com.xujinshan.service.UsersService;
import com.xujinshan.service.impl.UsersServiceImpl;

/**
 * spring 中加载properties文件
 * 		在src下新建xxx.properties 文件
 * 		在spring配置文件中先引入xmlns:context，在下面添加
 * 			如果需要记载多个配置文件逗号隔开
 * 			<context:property-placeholder location="classpath:db.properties"/>
 * 		添加了属性文件，并且在<bean>中开启自动注入注意的地方
 * 			SqlSessionFactoryBean 的id不能叫做sqlSessionFactory
 * 			修改
 * 				把原来通过ref引用替换成value赋值，自动注入只能影响ref，不会影响value赋值
 * 		在被spring管理的类中通过@Value("${key}") 取出properties中内容
 * 			添加注解扫描
 * 				<context:component-scan base-package="com.xujinshan.service.impl"></context:component-scan>
 * 			在类中添加
 * 				key和变量名可以不同
 * 				变量类型任意，只要保证key对应的value能转换成这个类型就行了
 * 				@Value("{my.demo"})
 * 				private String test;
 * @author xujinshan361@163.com
 *
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UsersService usersService;
	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		usersService = wac.getBean("usersService",UsersServiceImpl.class);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Users users = new Users();
		users.setUsername(req.getParameter("username"));
		users.setPassword(req.getParameter("password"));
		Users user = usersService.login(users);
		if(user!=null){
			resp.sendRedirect("main.jsp");
		}else{
			resp.sendRedirect("login.jsp");
		}
	}
}
