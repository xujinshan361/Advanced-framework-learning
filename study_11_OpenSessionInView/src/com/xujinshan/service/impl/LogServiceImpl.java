package com.xujinshan.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.xujinshan.mapper.LogMapper;
import com.xujinshan.pojo.Log;
import com.xujinshan.service.LogService;
import com.xujinshan.util.MyBatisUtil;

public class LogServiceImpl implements LogService{

	@Override
	public int ins(Log log) {
		SqlSession session = MyBatisUtil.getSession();
		LogMapper mapper = session.getMapper(LogMapper.class);
		return mapper.ins(log);
	}

}
