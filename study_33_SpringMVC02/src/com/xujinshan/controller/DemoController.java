package com.xujinshan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xujinshan.pojo.Demo;
import com.xujinshan.pojo.Demo02;
import com.xujinshan.pojo.People;

@Controller
public class DemoController {

	@RequestMapping("demo")
	// 只要写，SpringMVC能获取到值，就会赋值到这，
	public String demo(People peo,String name, int age,HttpServletRequest req) {
		System.out.println("执行demo"+peo+"  " + name +" " +age);
		req.setAttribute("demo", "测试");
		return "main.jsp";
	}
//	@RequestMapping("demo")
//	// 参数名不匹配时候使用
//	public String demo(@RequestParam(value="name1")String name, @RequestParam(value="age1")int age) {
//		System.out.println("demo");
//		return null;
//	}
	@RequestMapping("demo2")
	public String demo2() {
		System.out.println("执行demo2");
		return "main1.jsp";
	}
//	@RequestMapping("page")
//	public String page(Integer pageSize, Integer pageNumber) {
//		System.out.println(pageSize+"  "+ pageNumber);
//		return "main.jsp";
//	}
	
	@RequestMapping("page")
	public String page(@RequestParam(defaultValue = "2") int pageSize, @RequestParam(defaultValue = "1")int pageNumber) {
		System.out.println(pageSize+"  "+ pageNumber);
		return "main.jsp";
	}
	@RequestMapping("demo02")
	//没有值会将null赋值给name,required = true 如果没有传参数会报异常
	public String demo02(@RequestParam(required = true)String name) {
		System.out.println("name 是SQL的查询条件，必须要传递name参数"+name);
		return "main.jsp";
	}
	
	@RequestMapping("demo03")
	// defaultValue 和 required 不要一起使用，required没意义
	public String demo03(@RequestParam(required = true,defaultValue = "测试")String name) {
		System.out.println("demo03");
		return "main.jsp";
	}
	@RequestMapping("demo05")
	// 复选框传递参数
	public String demo05(String name, int age, @RequestParam("hover") List<String> abc) {
		System.out.println(name+"  " + age + "  " +abc);
		return "main.jsp";
	}
	
	@RequestMapping("demo06")
	public String demo06(Demo demo) {
		System.out.println(demo);
		return "main.jsp";
	}
	
	@RequestMapping("demo07")
	public String demo07(Demo02 demo) {
		System.out.println(demo);
		return "main.jsp";
	}
	@RequestMapping("demo08")
	public String demo08(String name, int age) {
		System.out.println(name+"  "+age);
		return "main.jsp";
	}
	
	@RequestMapping("demo09/{id1}/{name1}")
	// 正常起一样的参数名
	public String demo09(@PathVariable(value = "name1") String name, @PathVariable("id1") int age) {
		System.out.println(name + "  " + age);
		// 请求格式路径变化了，所有采用全路径
		return "/main.jsp";
	}
	@RequestMapping("demo10")
	public String demo10() {
		System.out.println("转发");
		return "/main.jsp";  // 省略forward:
	}
	
	@RequestMapping("demo11")
	public String demo11() {
		System.out.println("重定向");
		return "redirect:/main.jsp";
	}
	
	
	// 自定义视图解析器测试
	@RequestMapping("demo12")
	public String demo12() {
		return  "forward:demo13";   // 加前缀forward，走Spring默认解析器
	}
	@RequestMapping("demo13")
	public String demo13() {
		return "main";    // 视图解析器里面添加后缀.jsp ,会调到main.jsp 
	}
	
	@RequestMapping(value = "demo14",produces = "text/html;charset=utf-8")
	@ResponseBody()
	public People demo14(HttpServletResponse resp) throws IOException {
		People p = new People();
		p.setAge(12);
		p.setName("张三");
		return p;
	}
}
