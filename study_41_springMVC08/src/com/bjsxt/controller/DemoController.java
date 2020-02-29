package com.bjsxt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjsxt.pojo.Users;

/**
 * SpringMVC 运行原理
 * 		如果在web.xml 中设置DispatcherServlet 的<url-pattern> 为  / 时，当用户发起请求，请求一个控制器
 *		首先会执行DispatcherServlet、由DispatcherServlet 调用HandlerMapping的DefaultAnnotationHandlerMapping
 *		解析URL，解析后调用HandlerAdapter组件的AnnotationMethodHandlerAdapter 调用Controller中的
 *		HandlerMethod、当HandlerMethod执行完后会返回View ,会被ViewResolver 进行视图解析，解析后调用jsp对应的
 *		.class 文件并运行，最终把运行.class 文件的结果响应给客户端
 *		
 * @author xujinshan361@163.com
 *
 */
@Controller
public class DemoController {
	@RequestMapping("{page}")
	public String main(@PathVariable String page){
		System.out.println("restful");
		return page;
	}
	@RequestMapping("login")
	public String login(Users users,HttpSession session){
		if(users.getUsername().equals("admin")&&users.getPassword().equals("123")){
			session.setAttribute("users", users);
			return "main";
		}else{
			return "redirect:/login.jsp";
		}
	}
}
