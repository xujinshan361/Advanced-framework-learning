package com.xujinshan.dao;

import java.util.List;

import com.xujinshan.pojo.Flower;

public interface FlowerDao {

	/**
	 * 查询全部
	 * @return
	 */
	List<Flower> selectAll();
	/**
	 * 新增
	 * @param flower
	 * @return
	 */
	int insertFlower(Flower flower);
}
