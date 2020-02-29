package com.xujinshan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义拦截器
 * 		跟过滤器比较像的技术
 * 		发送请求时，被拦截器拦截，在控制器的前后添加额外功能
 * 			跟AOP区分开，AOP在特定方法前后扩充(对ServiceImpl)
 * 			拦截器，请求的拦截，针对点是控制器方法(对Controller)
 * 		
 * 		SpringMVC拦截器和Filter的区别
 * 			拦截器只能拦截Controller
 * 			Filter可以拦截任何请求
 * 		自定义拦截器的步骤
 * 			新建类实现HandlerInterceptor
 * 			在springmvc.xml 配置拦截器需要拦截哪些控制器
 * 				拦截所有控制器
 * 				<mvc:interceptors>
 *					<bean class="com.xujinshan.interceptor.DemoInterceptor"></bean>
 *				</mvc:interceptors>
 * 				拦截特定的url
 * 				<mvc:interceptors>
 *					<mvc:interceptor>
 *						<mvc:mapping path="/demo"/>
 *						<mvc:mapping path="/demo1"/>
 *						<mvc:mapping path="/demo2"/>
 *						<bean class="com.bjsxt.interceptor.DemoInterceptor"></bean>
 *					</mvc:interceptor>
 *				</mvc:interceptors>
 * 
 * @author xujinshan361@163.com
 *
 */
@Controller
public class DemoController {
	@RequestMapping("demo4")
	public String demo( Model model){
		System.out.println("执行demo");
//		int i = 5/0;
		model.addAttribute("model", "我们都爱祖国.");
		return "index.jsp";
	}
}
