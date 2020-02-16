package com.xujinshan.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.pojo.People;

/**
 * <settings>标签：
 * 		在mybatis全局配置文件中通过<settings>标签控制mybatis全局开关
 * 		在mybatis.xml 中开启log4j
 * 			必须保证有log4j.jar
 * 			在src下有log4j.properties
 * 		log4j中可以输出指定内容的日志(控制某个局部内容的日志级别)
 * 			命名级别(包级别)：<mapper>namespace 属性中除了最后一个类名
 * 			例如namespace="com.xujinshan.mapper.PeopleMapper"其中包级别为 com.xujinshan.mapper，需要在log4j.propeties
 * 			先在总体级别调成Error 不输出无用信息
 * 			在设置某个指定位置级别为DEBUG
 * 		类级别：
 * 			namespace 属性值，namespace类名
 * 		方法级别：
 * 			使用namespace属性值+标签id属性值
 * 
 * parameterType 属性
 * 		在 XXXMapper.xml 中<select><delete>等标签的parameterType可以控制参数类型
 * 		SqlSession 的selectList()和selectOne() 的第二个参数和selectMap()的第三个参数都表示方法的参数
 * 		示例：
 * 			People p = session.selectOne("com.xujinshan.mapper.PeopleMapper.selectById",1);
 * 
 * 		在Mapper.xml中可以通过#{}获取参数
 * 			parametType 控制参数类型
 * 			#{}获取参数类型
 * 				使用索引，从0开始，#{0}表示第一个参数
 * 				也可以使用#{param1}第一个参数
 * 				如果只有一个参数(基本数据类型或String)，mybatis 对#{}里面内容没有要求，只要写内容即可
 * 				如果参数是对象#{属性名}
 * 				如果参数是map，写成#{key}
 * 		
 * 		#{} 和${} 的区别
 * 			#{} 获取参数的内容支持，索引获取，param1 获取指定位置的参数，并且SQL使用？占位符
 * 			${} 字符串拼接不使用？，默认${内容} 内容的get/set 方法，如果写数字，就是一个数字
 * 	
 * 		如果在xml 文件中出现"<"">" ，双引号，等特殊字符时可以使用XML文件转义标签(XML 自身的)
 * 			<![CDATA[ 内容]]>
 * 
 * MyBatis 中实现mysql分页算法
 * 		? 不允许在关键字前后进行数学运算，需要在代码中计算完成后传递到mapper.xml中，在java代码中计算
 * 
 * MyBatis 别名
 * 		系统内置别名：把类型全部小写
 * 		给某个类起别名：
 * 			alias="自定义"
 * 		给某个包下所有类起别名，别名为类名，区分大小写
 * 			配置package
 * 		示例：
 * 			<typeAliases>
 * 				<typeAlias type="com.xujinshan.pojo.People" alias="po"/>
 * 				<package name="com.xujinshan.pojo"/>
 * 			</typeAliases>
 * 
 * 		功能：从应用程序角度出发，软件具有哪些功能
 * 		业务：完成功能时的逻辑，对应Service中的一个方法
 * 		事务：从数据库角度出发，完成业务时需要执行的SQL集合，统称一个事务
 * 			事务回滚：如果在一个事务中某个SQL执行事务，希望回归到事务的原点，保证数据库数据的完整性
 * 
 * 在MyBatis中默认关闭了JDBC的自动提交功能
 * 		每一个SQLSession默认都不是自动提交事务
 * 		session.commit() 提交事务
 * 		openSession(true);自动提交 setAutoCommit(true)
 * MyBatis 底层是对JDBC的封装
 * 		JDBC中executeUpdate()执行新增、删除、修改的SQL返回值int表示受影响的行数
 * 		MyBatis中<insert><delete><update> 标签没有resultType 属性，认为返回值都是int
 * 		在openSession() 时，MyBatis会创建SQLSession时同时会创建一个Transaction(事务对象)，同时autoCommit都为false
 * 		如果出现异常，应该session.rollback()回滚，使其回到原点
 * 		
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		session.selectList("com.xujinshan.mapper.PeopleMapper.select");
//		People people = session.selectOne("com.xujinshan.mapper.PeopleMapper.selectById",4);
//		System.out.println(people);
		
		// 如果希望传递多个参数，可以使用对象或map
		Map<String, Object> map = new HashMap<>();
		map.put("id", 1);
		map.put("name", "张三");
		People p = session.selectOne("com.xujinshan.mapper.PeopleMapper.selectById",map);
		System.out.println(p);
		
		Map<String, Object> map2 = new HashMap<>();
		// 显示几个
		int pageSize = 2;
		// 第几页
		int pageNumber = 1;
		map2.put("pageSize", pageSize);
		map2.put("pageStart",pageSize*(pageNumber-1));
		List<People> list2 = session.selectList("com.xujinshan.mapper.PeopleMapper.page",map2);
		System.out.println(list2);
		
		// 新增
		People p3= new People();
		p.setName("name1");
		p.setAge(34);
		int insert = session.insert("com.xujinshan.mapper.PeopleMapper.insert", p);
		// 测试事务回滚
		p.setName("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		/**
		 * 正常都需要进行try-catch ,在里面进行事务回滚
		 */
		try {
			int insert2 = session.insert("com.xujinshan.mapper.PeopleMapper.insert",p);
			System.out.println(insert);
		} catch (Exception e) {
			session.rollback();  // 事务回滚
		}
		session.commit();
		session.close();
		System.out.println("程序执行结束");
		
	}
}
