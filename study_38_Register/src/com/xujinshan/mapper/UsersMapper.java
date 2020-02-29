package com.xujinshan.mapper;

import org.apache.ibatis.annotations.Insert;

import com.xujinshan.pojo.Users;

public interface UsersMapper {
	@Insert("insert into t_users values (default,#{username},#{password},#{photo})")
	int insUsers(Users users);
}
