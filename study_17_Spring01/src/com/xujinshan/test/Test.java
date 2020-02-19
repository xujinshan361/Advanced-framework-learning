package com.xujinshan.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xujinshan.pojo.People;
import com.xujinshan.pojo.PeopleFactory;

/**
 * Spring 框架
 * 		主要发明者：Rod Johnson
 * 		轮子理论推崇者
 * 			轮子理论：不用重复发明轮子
 * 			IT行业：直接使用写好的代码
 * 		Spring框架宗旨：不重新发明技术，让原有技术使用起来更加方便
 * 		
 * 		Spring几大核心功能
 * 			IOC/DI		控制反转/依赖注入
 * 			AOP			面向切面编程
 * 			声明式事务
 * 		Spring框架 runtime
 * 			test: Spring 提供测试功能
 * 			Core Container:	核心容器，Spring启动的最基本的条件
 * 				Beans:	Spring负责创建类对象并管理对象
 * 				Core:  		核心类
 * 				Context:	上下文参数，获取外部资源或者管理注解等
 * 				SpEI :expression.jar
 * 			AOP: 实现aop功能需要依赖
 * 			Aspects： 切面Aop依赖的包
 * 			Data Access/Integration :Spring 封装数据访问层相关内容
 * 				JDBC：Spring对JDBC封装后的代码
 * 				ORM：封装了持久层框架的代码，例如Hibernate
 * 				transactions：对应Spring-tx.jar声明事务使用
 * 			WEB：需要Spring完成web相关功能时需要
 * 				例如：由Tomcat架子Spring 配置文件时需要有spring-web包
 * 
 * 		Spring框架中重要的概念
 * 			容器(Container):Spring 当作一个大容器
 * 			BeanFactory 接口，老版本
 * 				新版本中ApplicationContext接口，是BeanFactory子接口，BeanFactory的功能在ApplicationContext中都有
 * 		Spring3开始把Spring框架的功能拆分成为多个jar
 * 			Spring2以及以前就一个jar
 *
 * IOC(Inversion of Control) 控制反转
 * 		IOC是什么？
 * 			IOC完成的事原先由程序员主动通过new实例化对象的事情转交给Spring负责
 * 			控制反转中控制指的是：控制类的对象
 * 			控制反转中反转指的是：转交给Spring负责
 * 			IoC 最大的作用：解耦
 * 				程序员不需要管理对象，解除了对象管理和程序员之间的解耦
 * 
 * Spring环境搭建
 * 		导入jar		
 * 			四个核心包一个日志包
 * 				spring-beans
 * 				spring-context
 * 				spring-core
 * 				spring-expression
 * 				commons-logging
 * 		在src下新建applicationContext.xml
 * 			文件名称和路径自定义
 * 			Spring容器ApplicationContext，applicationContext.xml 配置的信息最终存储到了ApplicationContext容器中
 * 			Spring配置文件是基于schema
 * 				schema文件扩展名	.xsd
 * 				把schema理解成DTD的升级版
 * 					比DTD具备更好的扩展性
 * 				每次引入一个xsd文件是一个namespace(xmlns)
 * 			配置文件中只需要引入基本schema
 * 				通过<bean/>标签创建对象
 * 				默认配置文件加载时创建对象
 * 				
 * 				<?xml version="1.0" encoding="UTF-8"?>
 *				<beans xmlns="http://www.springframework.org/schema/beans"
 *				    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *				    xsi:schemaLocation="http://www.springframework.org/schema/beans
 *				        http://www.springframework.org/schema/beans/spring-beans.xsd">
 *				
 *					<!-- 
 *					id表示获取到对象的表示 
 *					class 创建的是哪个类
 *					-->
 *					<bean id="peo" class="com.xujinshan.pojo.People"></bean>
 *				</beans>
 * 			测试方法
 * 				getBean("<bean>标签id值"，返回值类型) 如果没有第二个参数，默认Object
 * 				getBeanDefinitionNames(),Spring 容器中目前所有管理的所有对象
 * 
 * Spring 创建对象的三种方式
 * 		通过构造方法创建	
 * 			无惨构造创建：默认情况
 * 			有参构造创建：需要明确配置
 * 				需要在类中提供有参构造方法
 * 				在applicationContext.xml中设置调用哪个构造方法创建对象
 * 					如果设定的条件匹配多个构造方法执行最后的构造方法
 * 					index：参数的索引，从0开始
 * 					name参数名
 * 					type类型(区分开关键字和封装类 	int和Integer)		
 * 				<bean id="peo" class="com.xujinshan.pojo.People">
 *					<!-- ref引用另一个bean，value基本数据类型或String -->
 *					<constructor-arg index="0" name="id" type="int" value="123"></constructor-arg>
 *					<constructor-arg index="1" name="name" type="java.lang.String" value="张三"></constructor-arg>
 *				</bean>
 *
 *		实例工厂
 *			工厂设计模式：帮助创建类对象，一个工厂可以生产多个对象
 *			实例工厂：需要先创建工厂，才能生产对象
 *			实现步骤：
 *				必须要有一个实现工厂
 *				public class PeopleFactory {
 *					public People newInstance() {
 *						return new People(1,"测试");
 *					}
 *				}
 *			在applicationContext.xml 中配置工厂对象和需要创建的对象
 *				<bean id="factory" class="com.xujinshan.pojo.PeopleFactory">
 *				</bean>
 *				<bean id="peo1" factory-bean="factory" factory-method="newInstance"></bean>
 *
 *		静态工厂
 *			编写一个静态工厂(在方法上加static)	
 *			public class PeopleFactory{
 *				public static People newInstance(){
 *					return new People(2,"测试");
 *				}
 *			}
 *			在applicationContext.xml中配置
 *
 *
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) {
//		People peo= new People();
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		People people = ac.getBean("peo",People.class);
//		System.out.println(people);
//		
//		String[] names = ac.getBeanDefinitionNames();
//		for(String name:names) {
//			System.out.println(name);
//		PeopleFactory factory = new PeopleFactory();
//		People people = factory.createPeople();
		
		/**
		 * 实例工厂测试
		 * 具体如何设计工厂，和这里无关
		 */
//		PeopleFactory factory = new PeopleFactory();
//		People people = factory.newInstance();
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		People people = ac.getBean("peo1",People.class);
//		System.out.println(people);
		
		/**
		 * 静态工厂
		 */
//		People people = PeopleFactory.newInstance2();
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		People people = ac.getBean("peo2",People.class);
		System.out.println(people);
	}
}
