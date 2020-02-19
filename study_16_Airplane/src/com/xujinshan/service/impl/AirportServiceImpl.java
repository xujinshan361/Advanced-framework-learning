package com.xujinshan.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xujinshan.mapper.AirportMapper;
import com.xujinshan.pojo.Airport;
import com.xujinshan.service.AirportService;
import com.xujinshan.util.MyBatisUtil;


public class AirportServiceImpl implements AirportService{

	@Override
	public List<Airport> showTakePort() {
		SqlSession session = MyBatisUtil.getSession();
		AirportMapper airportMapper = session.getMapper(AirportMapper.class);
		return airportMapper.selTakePort();
	}

	@Override
	public List<Airport> showLandPort() {
		SqlSession session = MyBatisUtil.getSession();
		AirportMapper airportMapper = session.getMapper(AirportMapper.class);
		return airportMapper.selLandPort();
	}

}
