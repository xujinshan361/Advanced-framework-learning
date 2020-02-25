package com.xujinshan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujinshan.mapper.MenuMapper;
import com.xujinshan.pojo.Menu;
import com.xujinshan.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuMapper menuMapper;

	@Override
	public List<Menu> show() {
		return menuMapper.selByPid(0);
	}
}
