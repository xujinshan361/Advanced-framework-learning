package com.xujinshan.service.impl;

import com.xujinshan.pojo.Users;
import com.xujinshan.service.UsersService;

public class UsersServiceImpl implements UsersService{

	@Override
	public int insert(Users users) {
		return 0;
	}
	@Override
	public int insUsers(Users users) {
		insert(users);
		return 0;
	}
}

