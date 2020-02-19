package com.xujinshan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xujinshan.pojo.Log;

public interface LogMapper {

	/**
	 * 查询所有
	 * @return
	 */
	List<Log> selectAll();
	
}
