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
	
	/**
	 * myBatis 把参数转换为map，其中@Param("key") 参数就是map里面的value
	 * @param accountIn
	 * @param accountOut
	 * @return
	 */
	List<Log> selectByAccountInAndAccountOut(String accountIn, String accountOut);    // 实现原理不一样
//	List<Log> selectByAccountInAndAccountOut(@Param("accountIn")String accountIn, @Param("accountOut")String accountOut);
	
	List<Log> selectAccInAccOut(@Param("accountIn")String accountIn, @Param("accountOut")String accountOut);

	int upd(Log log);
	
	List<Log> selectByLog(Log log);
	
	List<Log> selectIn(List<Integer> list);
	
	int ins (List<Integer> list);
}
