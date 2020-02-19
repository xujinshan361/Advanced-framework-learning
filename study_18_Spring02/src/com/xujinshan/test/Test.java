package com.xujinshan.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xujinshan.pojo.People;

/**
 * 如何给Bean的属性赋值(注入)
 * 		通过构造方法设置值
 * 		设置注入(通过set方法)
 * 			如果属性是基本数据类型或String等
 * 				<bean id="peo" class="com.bjsxt.pojo.People">
 *					<property name="id" value="222"></property>
 *					<property name="name" value=" 张三 "></property>
 *				</bean>	
 *			等效于
 *				<bean id="peo" class="com.bjsxt.pojo.People">
 *					<property name="id">
 *						<value>456</value>
 *					</property>
 *					<property name="name">
 *						<value>zhangsan</value>
 *					</property>
 *				</bean>	
 *			如果属性是Set<?>
 *				<property name="sets">
 *					<set>
 *						<value>1</value>
 *						<value>2</value>
 *						<value>3</value>
 *					</set>
 *				</property>
 *			如果属性是List<?>	
 *				<property name="list">
 *					<list>
 *						<value>1</value>
 *						<value>2</value>
 *						<value>3</value>
 *					</list>
 *				</property>	
 *			如果List中只有一个值
 *				<property name="list" value="1">
 *				</property>
 *			如果属性是数组
 *				如果数组中就只有一个值，可以直接通过value属性赋值
 *	 				<property name="strs">
 *						<array>
 *							<value>1</value>
 *							<value>2</value>
 *							<value>3</value>
 *						</array>
 *					</property>
 *			如果属性是map
 *				<property name="map">
 *					<map>
 *						<entry key="a" value="b"></entry>
 *						<entry key="c" value="d"></entry>
 *					</map>
 *				</property>
 *			如果属性是Properties 类型
 *				<property name="demo">
 *					<props>
 *						<prop key="key">value</prop>
 *						<prop key="key1">value1</prop>
 *					</props>
 *				</property>
 *
 *
 *DI (Dependency Injection)		依赖注入
 *		DI是什么
 *			DI和IoC 是一样的
 *			当一个类(A)中需要依赖另一个类(B)对象是，把B赋值给A的过程叫依赖注入
 *		示例：
 *			<property name="desk" ref="desk"></property>
 *			<bean id="desk" class="com.xujinshan.pojo.Desk">
 *				<property name="id" value="12"></property>	
 *				<property name="price" value="20"></property>
 *			</bean>
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		People people = ac.getBean("peo",People.class);
		System.out.println(people);
	}
}
