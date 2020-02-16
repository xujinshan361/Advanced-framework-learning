package com.xujinshan.service;

import java.util.List;

import com.xujinshan.pojo.Flower;

public interface FlowerService {
	/**
	 * 查询全部
	 * @return
	 */
	List<Flower> show(); 
	/**
	 * 新增
	 * @param flower
	 * @return
	 */
	int addFlower(Flower flower);
}
