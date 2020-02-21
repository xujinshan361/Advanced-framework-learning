package com.xujinshan.mapper;

import org.apache.ibatis.annotations.Select;

import com.xujinshan.pojo.Users;

public interface UsersMapper {
	@Select("select * from users where username=#{username} and password=#{password}")
	Users selectByUser(Users users);
}
