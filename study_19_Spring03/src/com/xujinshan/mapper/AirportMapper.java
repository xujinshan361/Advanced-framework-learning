package com.xujinshan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xujinshan.pojo.Airport;

public interface AirportMapper {

	@Select("select * from airport")
	List<Airport> selectAll();
}
