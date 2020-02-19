package com.xujinshan.service.impl;

import java.util.List;

import com.xujinshan.mapper.AirplaneMapper;
import com.xujinshan.pojo.Airplane;
import com.xujinshan.service.AirplaneService;
import com.xujinshan.util.MyBatisUtil;


public class AirplaneServiceImpl implements AirplaneService {

	@Override
	public List<Airplane> show(int takeid, int landid) {
		return MyBatisUtil.getSession().getMapper(AirplaneMapper.class).selByTakeidLandid(takeid, landid);
	}

}
