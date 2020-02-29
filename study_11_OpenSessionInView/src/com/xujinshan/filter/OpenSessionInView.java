package com.xujinshan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.ibatis.session.SqlSession;

import com.xujinshan.util.MyBatisUtil;

/**
 * 最开始是由Spring框架提出的，整合Hibernate框架是使用OpenSessionInView
 * @author xujinshan361@163.com
 *
 */
@WebFilter("/*")   // 注解方式配置过滤器
public class OpenSessionInView implements Filter{

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {
//		InputStream is = Resources.getResourceAsStream("mybatis.xml");
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
//		SqlSession session = factory.openSession();		
		SqlSession session = MyBatisUtil.getSession();
		try {
			filterchain.doFilter(request, response);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSession();
		}
//		session.commit();
//		session.close();
	}
}
