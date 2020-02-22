package com.xujinshan.test;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *使用注解(基于Aspect)
 *		spring不会自动去寻找注解，必须告诉spring哪些包下的类中可能有注解
 *		引入xmlns:context
 *			<context:component-scan base-package="com.xujinshan.advice"></context:component-scan>
 *		@Component 
 *			相当于<bean />标签
 *			如果没有参数，把类名字母变小写，相当于<bean id="" />
 *			@Component("自定义名称")
 *		
 *		实现步骤：
 *			在spring配置文件中设置注解在哪些包中
 *				 <context:component-scan base-package="com.xujinshan.advice,com.xujinshan.test"></context:component-scan>
 *			在Demo类中添加@Componet 
 *				在方法上添加@Pointcut("")定义切点
 *			在通知类中配置
 *				@Component 类被spring管理
 *				@Aspect 相当于<aop:aspect /> 表示通知方法在当前类中
 * @author xujinshan361@163.com
 *
 */
public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		String[] names = ac.getBeanDefinitionNames();
//		System.out.println(Arrays.toString(names));
		Demo demo = ac.getBean("demo",Demo.class);
		try {
			demo.demo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
}
