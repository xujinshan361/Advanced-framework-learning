package com.xujinshan.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 异常通知(Schema-based 方式)
 * 		必须自己写方法，且必须叫afterThrowing
 * 		有俩种参数方式
 * 			必须是1个或4个
 * 		异常类型要与切点报的异常类型一致
 *
 *环绕通知(Schema-based)方式
 *		把前置通知和后置通知都写到一个通知中，组成了环绕通知		
 * 		实现步骤：
 * 			新建一个类实现MethodInterceptor
 * 				Object result = arg0.proceed(); // 放行，调用切点方法
 * 			配置applicationContext.xml 
 * @author xujinshan361@163.com
 *
 */
public class Test {
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Demo demo = ac.getBean("demo",Demo.class);
		try {
			demo.demo1();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
