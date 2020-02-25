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
 *		编写控制器类
 *
 *字符编码过滤器
 *		在web.xml 中配置Filter
 *
 *传参
 *		把内容写到(HandlerMethod) 参数中，SpringMVC只要有这个内容，注入内容
 *
 *		基本参数类型参数
 *			默认保证参数名称和请求中传递的参数名相同
 *			如果请求参数和方法名参数不对应使用RequestParam() 赋值
 *			如果参数是基本数据类型(不是封装类) 可以通过@RequestParam设置默认值
 *				防止没有参数时500
 *			如果强制要求必须有某个参数--使用required = true
 *		
 *		HandlerMethod中参数时对象类型
 *			请求参数名和对象中属性名对应(get/set 方法)
 *			注意：没有注解可以解决请求参数名和属性名不相同情况
 *
 *		请求参数中包含多个同名参数的获取方式
 *			复选框传递的参数就是多个同名参数
 *		
 *		请求参数中  对象.属性  格式
 *			新建一个类
 *				对象名和参数中点前面名称对应
 *			示例demo06
 *		
 *		请求参数中传递集合对象类型参数
 *			示例demo07	
 *
 *		restful传值方式
 *			简化jsp中参数编写格式
 *			在jsp中设定特定的格式
 *				<a href="demo09/123/abc">跳转</a>
 *			在控制器中
 *				在@RequestMapping 中一定要和请求格式对应
 *				{名称} 中名称自定义名称
 *				@PathVariable 获取@RequestMapping 中内容，默认安装方法参名去寻找
 *			示例：demo09
 *跳转方式
 *		默认跳转方式请求转发
 *		设置返回值字符串内容
 *			添加redirect:资源路径   			重定向
 *			添加forward:资源路径  或省略forward   	转发
 *
 *视图解析器
 *		SpringMVC会提供默认视图解析器
 *		程序员自定义视图解析器
 *			org.springframework.web.servlet.view.InternalResourceViewResolver
 *		如果希望不执行自定义视图解析器，在方法返回值前添加forward: 或redirect:
 *
 *@ResponseBody
 *		在方法上只有@RequestMapping时，无论方法返回值是什么都认为需要跳转
 *
 *		在方法上添加@ResponseBody (恒不跳转)
 *			如果返回值满足key-value 形式(对象或map)
 *				把响应头设置为   application/json;charset=utf-8
 *				把转换后的内容以输出流的形式响应给客户端
 *			如果返回值不满足key-value ，例如返回值为String
 *				把响应头设置为text/html
 *				把方法返回值以流的形式直接输出
 *				如果返回值包含中文，出现乱码
 *					produces 表示响应头中Context-type取值
 *
 *		底层使用Jackson 进行json 转换，在项目中一定要导入jackson的jar
 *			spring 4.1.6 对Jackson不支持较高版本，jackson 2.7无效
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
