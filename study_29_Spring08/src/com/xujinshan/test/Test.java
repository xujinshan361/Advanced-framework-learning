package com.xujinshan.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动注入
 * 		在Spring 配置文件中对象名和 ref="id" id名相同使用自动注入，可以不配置<property/>
 * 		俩种配置办法
 * 			在<bean> 中通过autowire=""配置，只对这个<bean>生效
 * 			在<beans> 中通过default-autowire="" 配置，表示当前文件中所有<bean>都是全局配置内容
 * 
 * 		autowrie=""可取值
 * 			default：默认值，根据全局default-autowrie=""值，默认全局和局部都没配置的情况下相当于no
 * 			no:不自动注入
 * 			byName:通过名称自动注入，在spring容器中找类的id
 * 			byType:根据类型注入
 * 				spring容器中不可以出现俩个相同类型的<bean>
 * 			constructor:根据构造方法注入
 * 				提供对应参数的构造方法(构造方法参数中包含注入对象那个)
 * 				底层使用byName,构造方法参数名和其他<bean>的id相同
 * 
 * scope属性
 * 		<bean>的属性
 * 		作用：控制对象有效范围(单例，多例等)
 * 		<bean/>标签对应的对象默认是单例的
 * 			无论获取多少次，都是同一个对象
 * 		scope可取值
 * 			singleton：默认值，单例
 * 			prototype:多例，每次获取重新实例化
 * 			request：每次请求重新实例化
 * 			session：每个会话对象内，对象是单例
 * 			application：在application对象内是单例
 * 			global session spring 推出的一个对象，依赖于spring-webmvc-portlet，类似于session
 * @author xujinshan361@163.com
 *
 */
public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		People peo = ac.getBean("people",People.class);
//		System.out.println(peo);
		
		Teacher teacher1 = ac.getBean("teacher1",Teacher.class);
		Teacher teacher2 = ac.getBean("teacher1",Teacher.class);
		System.out.println(teacher1==teacher2);
	}
}
