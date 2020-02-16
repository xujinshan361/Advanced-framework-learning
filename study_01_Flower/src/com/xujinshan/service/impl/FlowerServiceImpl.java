package com.xujinshan.service.impl;

import java.util.List;

import com.xujinshan.dao.FlowerDao;
import com.xujinshan.pojo.Flower;
import com.xujinshan.service.FlowerService;
import com.xujinshan.dao.impl.FlowerDaoImpl;

public class FlowerServiceImpl implements FlowerService{
	private FlowerDao flowerDao = new FlowerDaoImpl();
	@Override
	public List<Flower> show() {
		return flowerDao.selectAll();
	}
	@Override
	public int addFlower(Flower flower) {
		return flowerDao.insertFlower(flower);
	}
}
