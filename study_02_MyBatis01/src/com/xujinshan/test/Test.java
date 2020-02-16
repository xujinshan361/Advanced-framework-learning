package com.xujinshan.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.pojo.Flower;

/**
 * 框架是什么：
 * 		框架是软件的半成品，未解决问题制定的一套约束，在提供功能基础上进行扩展
 * 		框架中一些不能被封装的代码(变量)，需要使用框架者新建一个xml文件，在文件中添加变量内容
 * 			需要建立特定位置和特定名称的配置文件
 * 			需要使用xml解析技术和反射技术
 * 		常用概念：
 * 			类库：提供的类没有封装一定逻辑
 * 				例如：类库是名言警句，写作文时引入名言警句
 * 			框架：区别与类库，里面有一些约束
 * 				例如：框架是填空题
 * MyBatis 简介:
 * 		MyBatis 开源免费框架，原名叫iBatis,2010 年在google code,2013年迁移到github
 * 		作用：		
 * 			数据访问层框架
 * 			底层是对JDBC的封装
 * 		MyBatis优点：
 * 			使用MyBatis时不需要编写实现类，只需要写执行sql的命令
 * 		
 * 		环境搭建：
 * 			导入jar
 * 			在src下新建全局配置文件(编写JDBC四个变量)
 * 				没有名称和地址要求
 * 				在全局配置文件中引入DTD或schema
 * 				注意：		
 * 					如果导入DTD后没有提示
 * 						window-->preference -->XML-->XML catalog-->add 按钮
	  			全局配置文件：
	  			<?xml version="1.0" encoding="utf-8"?>
	  			<<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
				<configuration>
					<!-- default引用environment的id ，当前所使用的环境 -->
					<environments default="default">
					<!-- 声明可以使用的环境 -->
						<environment id="default">
							<!-- 使用原生JDBC事务 -->
							<transactionManager type="JDBC"></transactionManager>
							<dataSource type="POOLED">
								<property name="driver" value="com.mysql.jdbc.Driver"/>
								<property name="url" value="jdbc:mysql://localhost:3306/ssm"/>
								<property name="username" value="root"/>
								<property name="password" value="123456"/>
							</dataSource>
						</environment>
					</environments>
					<mappers>
						<mapper resource="com/xujinshan/mapper/FlowerMapper.xml"/>
					</mappers>
				</configuration>
 * 		新建以mapper结尾的包，在包下新建：实体类名+Mapper.xml
 * 			文件作用：编写需要执行的SQL命令
 * 			把xml文件理解成实现类
 * 			XML文件内容：
				<?xml version="1.0" encoding="UTF-8"?>
				<!DOCTYPE mapper 
				PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
				"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
				<!-- namespace ：理解成实现类的全路径 -->
				<mapper namespace="com.xujinshan.mapper.FlowerMapper">
					<!-- 
						id:方法名
						parameterType:定义参数类型
						resultType:返回值类型
						
						如果方法返回值是List，在resultType中写List的泛型，
						因为mybatis对JDBC封装，一行一行读取数据
					 -->
					<select id="selectAll" resultType="com.xujinshan.pojo.Flower">
						select * from flower
					</select>
					<select id="selectById" resultType="int"><!-- Integer -->>
						select  count(*) from flower where id=1
					</select>
					<select id="c" resultType="com.xujinshan.pojo.Flower">
						select id,name,price,production from flower
					</select>
				</mapper>
		
 * @author xujinshan361@163.com
 *
 */
public class Test {
	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		// 使用工厂设计模式
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		// 生产SqlSession
		SqlSession session = factory.openSession();
		
		List<Flower> list = session.selectList("com.xujinshan.mapper.FlowerMapper.selectAll");
		for(Flower flower:list) {
			System.out.println(flower.toString());
		}
		
//		int count = session.selectOne("com.xujinshan.mapper.FlowerMapper.selectById");
//		System.out.println(count);
//		
		// 把数据库中那个列的值当做map的key
		Map<Object, Object> map = session.selectMap("com.xujinshan.mapper.FlowerMapper.c", "name");
		System.out.println(map);
		session.close();
	}
}
