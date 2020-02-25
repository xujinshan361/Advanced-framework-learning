package com.xujinshan.test;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;

/**
 * SpringMVC
 * 		SpringMVC 中重要组件：
 * 			DispatcherServlet：前端控制器，接收所有请求(如果配置/ 不包含jsp)
 * 		
 * 			HandlerMapping：解析请求格式的，判断希望要执行哪个具体的方法
 * 			
 * 			HandlerAdapter：负责调用具体的方法
 * 	
 * 			ViewResovler:视图解析器，解析结果，准备调到那个具体的物理视图
 * 
 *		SpringMVC运行原理图
 *			---> DisptcherServlet ---> HandlerMapping ---> HandlerAdapter ---> Controller-->
 *		
 *		Spring 容器和SpringMVC容器是父子容器
 *			SpringMVC容器能调用Spring容器的所有内容
 *
 *SpringMVC环境搭建
 *		导入jar
 *			spring-webmvc和spring的核心jar
 *		在web.xml 中配置前端控制器 DispatcherServlet
 *			如果不配置<init-param> 会在/WEB-INF/<servlet-name>-servlet.xml
 *		在src下新建springmvc.xml
 *			引入xmlns:mvc 命名空间
 *
 *		编写控制器类
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = null;
		HandlerMapping hm = null;
		HandlerAdapter ha = null;
		ViewResolver vr = null;
	}
}
