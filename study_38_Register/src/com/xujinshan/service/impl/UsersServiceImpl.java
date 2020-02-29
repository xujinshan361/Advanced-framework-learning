package com.xujinshan.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xujinshan.mapper.UsersMapper;
import com.xujinshan.pojo.Users;
import com.xujinshan.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	@Resource
	private UsersMapper usersMapper;
	@Override
	public int insRegister(Users users) {
		return usersMapper.insUsers(users);
	}

}
