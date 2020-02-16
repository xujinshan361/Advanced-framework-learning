package com.xujinshan.service;

import java.io.IOException;
import java.util.List;

import com.xujinshan.pojo.People;

public interface PeopleService {
	
	/**
	 * 显示全部
	 * @return
	 * @throws IOException 
	 */
	List<People> show() throws IOException;
}
