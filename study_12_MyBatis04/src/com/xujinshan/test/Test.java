package com.xujinshan.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.mapper.LogMapper;
import com.xujinshan.pojo.Log;

/**
 *	缓存
 *		应用程序和数据交互的过程是一个相对比较耗时的过程
 *		缓存存在的意义：让应用程序减少对数据的访问，提升程序运行的效率
 *	MyBatis 中默认SqlSession缓存开启
 *		同一个SqlSession对象调用同一个<select>时，只有第一次访问数据库，第一次之后把查询结果缓存到SqlSession缓存区(内存)中
 *		缓存的是statement对象(简单记忆必须是同一个select)
 *			在mybatis时一个select对应一个statement对象
 *		有效范围必须是同一个SqlSession对象
 *	
 *		缓存流程：
 *			步骤一：先去缓存区中找是否存在statement
 *			步骤二：返回结果
 *			步骤三：如果没有缓存的statement对象，去数据库获取数据
 *			步骤四：数据库返回查询结果
 *			步骤五：把查询结果放到对应的缓存区中
 *	
 *	SqlSessionFactory缓存
 *		又叫：二级缓存
 *		有效范围：同一个factory内哪个SqlSession都可以获取
 *		什么时候使用二级缓存
 *			当数据频繁被使用，很少被修改
 *		使用二级缓存步骤
 *			在mapper.xml 中添加
 *			如果不写readOnly="true" 需要把实体类序列化
 *			<cache readOnly="true"></cache>
 *		当SqlSession对象close() 时，或commit()时会把SqlSession 缓存的数据刷(flush) 到SqlSessionFactory缓存区中	
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		// 
		LogMapper logMapper = session.getMapper(LogMapper.class);
		logMapper.selectAll();
		logMapper.selectAll();
		session.commit();
		session.close();
		
		// 不同的session对象
		session = factory.openSession();
		logMapper = session.getMapper(LogMapper.class);
		logMapper.selectAll();
		session.commit();
		session.close();
	}
}
