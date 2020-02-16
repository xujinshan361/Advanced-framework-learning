package com.xujinshan.service;

import java.io.IOException;

import com.xujinshan.pojo.PageInfo;

public interface PeopleService {

	PageInfo showPage(int pageSize, int pageNumber) throws IOException;
}
