package com.xujinshan.service.impl;

import org.springframework.beans.factory.annotation.Value;

import com.xujinshan.mapper.UsersMapper;
import com.xujinshan.pojo.Users;
import com.xujinshan.service.UsersService;

public class UsersServiceImpl implements UsersService {
	@Value("${my.demo}")
	private String test;
	private UsersMapper usersMapper;
	public UsersMapper getUsersMapper() {
		return usersMapper;
	}
	public void setUsersMapper(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}
	@Override
	public Users login(Users users) {
		System.out.println("输出"+test);
		return usersMapper.selByUsers(users);
	}

}
