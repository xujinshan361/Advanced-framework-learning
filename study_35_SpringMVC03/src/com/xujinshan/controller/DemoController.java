package com.xujinshan.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * JSP九大内置对象和四大作用域
 * 		九大内置对象
 * 		名称			类型						含义					获取方式
 * 		request		HttpServletRequest		封装所有请求信息			方法参数
 * 		response	HttpServletResponse		封装响应信息			方法参数
 * 		session		HttpSession				封装所有会话信息			req.getSession()
 * 		application ServletContext			所有信息				getServletContext() / request.getServletContext()
 * 		out			PrintWriter 			输出对象				response.getWriter()
 * 		exception	Exception				异常对象				
 * 		page 		Object					当前页面对象			
 * 		pageContext PageContext				获取其他对象
 * 		config		ServletConfig			配置信息
 * 
 * 		四大作用域
 * 		page
 * 			在当前页面不会重新实例化
 * 		request
 * 			在一次请求中同一个对象，下次请求重新实例化一个request对象
 * 		session
 * 			一次会话
 * 			只要客户端Cookie 中传递的Jsessionid 不变，session不会重新实例化(不超过默认时间)
 * 			实际有效时间
 * 				浏览器关闭，Cookie失效
 * 				默认时间，在时间范围内无任何交互，在Tomcat的web.xml中配置
 * 		application 
 * 			只有在Tomcat启动项目时才实例化，关闭Tomcat是销毁application
 * 	
 * 
 * SpringMVC 作用域传值的几种方式
 * 		使用原生servlet
 * 			在HandlerMethod 参数中添加作用域对象
 * 		使用Map集合
 * 			把map内容放在request作用域中
 * 			spring会对map集合通过BindingAwareModelMap进行实例化
 * 		使用SpringMVC中Model接口
 * 			把内容最终放入到request作用域中	
 * 		使用SpringMVC中ModelAndView 类
 * @author xujinshan361@163.com
 *
 */
@Controller
public class DemoController {
	@RequestMapping("demo1")
	public String demo(HttpServletRequest req, HttpSession sessionParam) {
		// request作用域
		req.setAttribute("req", "req的值");
		// session作用域
		HttpSession session = req.getSession();
		session.setAttribute("session", "session的值");
		sessionParam.setAttribute("sessionParam", "sessionParam的值");
		// application 作用域
		ServletContext application = req.getServletContext();  
		application.setAttribute("application", "application 的值");
		return "/index";   // 建议写全路径
	}
	
	@RequestMapping("demo2")
	public String demo2(Map<String, Object> map) {
		System.out.println(map.getClass());
		map.put("map", "map的值");
		return "index";
	}
	@RequestMapping("demo3")
	public String demo2(Model model) {
		model.addAttribute("model", "model 的值");
		return "/index";
	}
	@RequestMapping("demo4")
	public ModelAndView demo4() {
		// 参数表示跳转视图
		ModelAndView mav = new ModelAndView("/index");
		mav.addObject("mav", "mav的值");
		return mav;
	}
}
