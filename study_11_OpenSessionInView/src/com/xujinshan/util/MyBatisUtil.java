package com.xujinshan.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	// factory 实例化的过程是一个比较耗费性能的过程
	// 保证有且只有一个factory
	private static SqlSessionFactory factory;
	// ThreadLocal线程容器，给线程绑定一个Object内容，后只要线程不变，可以随时取出
	// 改变线程，无法取出
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	static {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取SqlSession
	 * @return
	 */
	public static SqlSession getSession() {
		SqlSession session =threadLocal.get();
		if(session == null) { // 如果没有就创建
			threadLocal.set(factory.openSession());
		}
		return threadLocal.get();
	}
	public static void closeSession() {
		if(threadLocal.get()!=null) {
			threadLocal.get().close();
		}
		threadLocal.set(null);
	}
}
