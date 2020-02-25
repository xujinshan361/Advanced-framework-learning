package com.xujinshan.mapper;

import java.util.List;

import com.xujinshan.pojo.Menu;

public interface MenuMapper {
	List<Menu> selByPid(int pid);
}
