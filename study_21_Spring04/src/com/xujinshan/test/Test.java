package com.xujinshan.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AOP (Aspect Oriented Programming) 面向切面编程
 * 		正常程序执行流程都是纵向执行流程
 * 			又叫面向切面编程，在原有纵向执行流程中添加横切面
 * 			不需要修改原有程序代码
 * 				高扩展性
 * 				原有功能相当于释放了部分逻辑，让职责更加明确
 * 			示图：
 * 					demo1
 * 					  |
 * 					  |
 * 		     前置通知------demo2----后置通知
 * 					  |
 * 					  |
 * 					demo3
 * 		面向切面编程是什么
 * 			在程序原有纵向执行流程中，针对某一个或某些方法添加通知，形成横切面的过程叫做面向切面编程
 * 		
 * 		常用概念：
 * 			原有功能：切点，pointcut
 * 			前置通知：在切点之前执行的功能。 before advice;
 * 			后置通知：在切点之后执行的功能。after advice;
 * 			如果切点执行过程中出现异常，会触发异常通知。throws advice;
 * 			所有功能总称叫切面
 * 			织入：把切面嵌入到原有功能的过程叫织入
 * 
 * Spring 提供了2中AOP实现方式
 * 		Schema-based
 * 			每个通知都要实现接口或类
 * 			配置spring 配置文件是在<aop:config>
 * 		AspectJ
 * 			每个通知不需要实现接口或类
 * 			配置spring配置文件是在<aop:config>的子标签<aop:aspect>中配置
 * 
 * Schema-based实现
 * 		导入jar包
 * 		新建前置通知类
 * 		
 * 		新建后置通知类
 * 		
 * 		配置spring配置文件
 * 			引入aop命名空间
 * 			配置通知类<bean>
 * 			配置切面
 * 			*通配符，匹配任意方法名，任意类名，任意一级包名
 * 			如果希望匹配任意方法参数(..)
 * 
 * 配置异常通知的步骤(AspectJ 方式)
 * 		只有当切点报异常才能触发异常通知
 * 		在spring 中 AspectJ方式提供了异常的通知的办法
 * 			如果希望通知schema-base实现需要安照特定的要求自己编写方法
 * 		实现步骤：
 * 			新建类，在类中写任意方法
 * 			public class MyThrowAdvice {
 *				public void myexecption(Exception e) {
 *					System.out.println("执行异常通知，异常message："+e.getMessage());
 *				}
 *			}
 *			
 *			在spring配置文件中配置
 *				<aop:aspect> 的ref属性表示：方法在哪个类中
 *				<aop:xxx/> 表示什么通知
 *				method :当触发这个通知时，调用哪个方法
 *				throwing :异常对象名，必须和通知中方法参数名相同(可以不在通知中声明异常对象)
 *				
 *				<bean id="demo" class="com.xujinshan.test.Demo"></bean>
 *			 	<bean id="mythrow" class="com.xujinshan.advice.MyThrowAdvice"></bean>	
 *			 	<aop:config>
 *			 		<aop:aspect ref="mythrow">
 *			 			<aop:pointcut expression="execution(* com.xujinshan.test.Demo.demo1())" id="mypoint"/>
 *			 			<aop:after-throwing method="myexecption" pointcut-ref="mypoint" throwing="e"/>
 *			 			
 *			 		</aop:aspect>
 *			 	</aop:config>
 *
 *
 *
 * @author xujinshan361@163.com
 *
 */

public class Test {
	public static void main(String[] args) {
//		Demo demo = new Demo();
//		demo.demo1();
//		demo.demo2();
//		demo.demo3();
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		Demo demo = ac.getBean("demo",Demo.class);
//		demo.demo1();
//		demo.demo2();
//		demo.demo3();
//		demo.demo4("传递的参数");
//		Demo1 demo1 = ac.getBean("demo1",Demo1.class);
//		demo1.demo11();
		
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Demo demo = ac.getBean("demo",Demo.class);
		try {
			demo.demo1();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
