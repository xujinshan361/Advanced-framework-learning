package com.xujinshan.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	// factory 实例化的过程是一个比较耗费性能的过程
	// 保证有且仅有一个factory
	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	static {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取SqlSession 的方法
	 * @return
	 */
	public static SqlSession getSession() {
		SqlSession session = threadLocal.get();
		if(session==null) {
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
