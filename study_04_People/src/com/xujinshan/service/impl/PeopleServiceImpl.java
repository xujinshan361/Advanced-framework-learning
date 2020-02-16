package com.xujinshan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.pojo.People;
import com.xujinshan.service.PeopleService;

/**
 * 在数据访问层处理异常和控制器中处理异常，service中只抛出异常
 * 
 * @author xujinshan361@163.com
 *
 */
public class PeopleServiceImpl implements PeopleService{

	@Override
	public List<People> show() throws IOException {
		InputStream is= Resources.getResourceAsStream("mybatis.xml"); 
		// 前面是工厂		实例化工厂对象时使用的是 构建者模式， 名称标志：后面有Builder
		// 构建者设计模式存在的意义：简化实例化对象过程
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		List<People> list = session.selectList("com.xujinshan.mapper.PeopleMapper.selectAll");
		
		session.close();
		
		return list;
	}
}
