package com.xujinshan.service.impl;

import com.xujinshan.mapper.UsersMapper;
import com.xujinshan.pojo.Users;
import com.xujinshan.service.UsersService;

public class UsersServiceImpl implements UsersService{

	private UsersMapper usersMapper;
	
	public UsersMapper getUsersMapper() {
		return usersMapper;
	}

	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	@Override
	public Users login(Users users) {
		return usersMapper.selectByUser(users);
	}
}
