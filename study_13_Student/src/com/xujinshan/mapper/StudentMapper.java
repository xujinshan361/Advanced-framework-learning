package com.xujinshan.mapper;

import java.util.List;

import com.xujinshan.pojo.PageInfo;
import com.xujinshan.pojo.Student;

public interface StudentMapper {

	List<Student> selByPage(PageInfo pi);
	
	long selCountByPageInfo(PageInfo pi);
}
