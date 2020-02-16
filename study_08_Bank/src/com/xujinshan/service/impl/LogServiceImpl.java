package com.xujinshan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.pojo.Log;
import com.xujinshan.pojo.PageInfo;
import com.xujinshan.service.LogService;

public class LogServiceImpl implements LogService{

	@Override
	public PageInfo showPage(int pageSize, int pageNumber) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageStart",pageSize*(pageNumber-1));
		param.put("pageSize", pageSize);
		List<Log> list = session.selectList("com.xujinshan.mapper.LogMapper.selectByPage",param);
		long count = session.selectOne("com.xujinshan.mapper.LogMapper.selectCount");
		PageInfo pi = new PageInfo();
		pi.setList(list);
		pi.setPageNumber(pageNumber);
		pi.setPageSize(pageSize);
		pi.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
		session.commit();
		session.close();
		return pi;
	}
}
