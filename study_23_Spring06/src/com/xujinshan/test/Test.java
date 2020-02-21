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
 * 
 * 使用AspectJ 方式实现
 * 		新建类，不用实现
 * 			类中方法名任意
 * 		配置spring配置文件
 * 			<aop:after /> 后置通知，是否释放出现异常都执行
 * 			<aop:after-returning /> 后置通知，只有当切点正确执行时执行
 * 			<aop:after /> <aop:agter-returning />和<aop:after-throwing /> 执行顺序和配置顺序有关
 * 			execution() 括号不能扩上args
 * 			中间使用and不能使用&& ，由spring把and解析成&&
 * 			args(名称) 名称自定义的，顺序和demo(参数，参数)对应
 * 			<aop:before /> arg-names="名称" 名称来源于expression="" 中args(),名称必须一样
 * 			args() 里有几个参数， arg-names 里面必须有几个参数
 * 			arg-names=""里面名称必须和通知方法参数对应
 * @author xujinshan361@163.com
 *
 */
public class Test {
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Demo demo = ac.getBean("demo",Demo.class);
		try {
			demo.demo("张三",23);
			demo.demo1("李四");
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
