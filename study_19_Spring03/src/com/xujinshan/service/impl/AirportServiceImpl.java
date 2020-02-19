package com.xujinshan.service.impl;

import java.util.List;

import com.xujinshan.mapper.AirportMapper;
import com.xujinshan.pojo.Airport;
import com.xujinshan.service.AirportService;

public class AirportServiceImpl implements AirportService{

	private AirportMapper airportMapper;
	
	public AirportMapper getAirportMapper() {
		return airportMapper;
	}

	public void setAirportMapper(AirportMapper airportMapper) {
		this.airportMapper = airportMapper;
	}

	@Override
	public List<Airport> show() {
		return airportMapper.selectAll();
	}
}
