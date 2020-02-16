package com.xujinshan.service;

import java.io.IOException;

import com.xujinshan.pojo.PageInfo;

public interface LogService {

	/**
	 * 分页显示
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 * @throws IOException 
	 */
	PageInfo showPage(int pageSize, int pageNumber) throws IOException;
}
