package com.xujinshan.service;

import com.xujinshan.pojo.PageInfo;

public interface StudentService {

	PageInfo showPage(String sname, String tname, String pageSize, String pageNumber);
}
