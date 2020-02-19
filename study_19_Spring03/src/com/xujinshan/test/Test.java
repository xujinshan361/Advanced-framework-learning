package com.xujinshan.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xujinshan.pojo.Airport;
import com.xujinshan.service.AirportService;
import com.xujinshan.service.impl.AirportServiceImpl;

/**
 * 使用Spring 简化MyBatis
 * 
 * 		导入mybatis 所有jar包，sprig基本包
 * 		spring-jdbc,spring-tx,spring-aop,spring-web,spring整合mybatis的包等
 * 
 * 		配置web.xml文件
 * 		
 * 		编写spring配置文件applicationContext.xml
 * 
 * 		编写代码
 * 			正常编写pojo
 * 			编写mapper包下时必须使用接口绑定方案或者注解方案(必须有接口)
 * 			正常编写service接口和service实现类
 * 				需要在service实现类中声明Mapper接口对象，并生成set/get方法
 * 			spring无法管理servlet，在service中提取service对象
 * 
 * 
 * @author xujinshan361@163.com
 *
 */
public class Test {
	
	public static void main(String[] args) {
//		//ClassPathXmlApplicationContext 默认去classes文件夹根目录开始寻找
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		String[] names = ac.getBeanDefinitionNames();
//		for(String string:names) {
//			System.out.println(string);
//		}
		
		AirportService bean = ac.getBean("airportService",AirportServiceImpl.class);
		List<Airport> list = bean.show();
		System.out.println(list);
	}
}
