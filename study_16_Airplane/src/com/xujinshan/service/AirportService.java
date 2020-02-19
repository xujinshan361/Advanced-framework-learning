package com.xujinshan.service;

import java.util.List;

import com.xujinshan.pojo.Airport;


public interface AirportService {
	/**
	 * 显示所有起飞机场
	 * @return
	 */
	List<Airport> showTakePort();
	/**
	 * 显示所有降落机场
	 * @return
	 */
	List<Airport> showLandPort();
}
