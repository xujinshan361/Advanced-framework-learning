package com.xujinshan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.pojo.PageInfo;
import com.xujinshan.service.PeopleService;

public class PeopleServiceImpl implements PeopleService{

	@Override
	public PageInfo showPage(int pageSize, int pageNumber) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		PageInfo pi = new PageInfo();
		pi.setPageSize(pageSize);
		pi.setPageNumber(pageNumber);
		Map<String, Object> map = new HashMap<>();
		map.put("pageStart", pageSize*(pageNumber-1));
		map.put("pageSize",pageSize);
		pi.setList(session.selectList("com.xujinshan.mapper.PeopleMapper.selectByPage",map));
		
		// 总条数
		long count = session.selectOne("com.xujinshan.mapper.PeopleMapper.selectCount");
		pi.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
		return pi;
		
	}
}
