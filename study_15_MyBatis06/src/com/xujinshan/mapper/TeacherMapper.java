package com.xujinshan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xujinshan.pojo.Teacher;

public interface TeacherMapper {

	// 实现查询
	@Select("select * from teacher")
	List<Teacher> selectAll();
	
	// 实现新增
	@Insert("insert into teacher values(default,#{name})")
	int insertTeacher (Teacher teacher);
	
	// 实现修改
	@Update("update teacher set name=#{name} where id=#{id}")
	int updateTeacher(Teacher teacher);
	
	// 实现删除
	@Delete("delete from teacher where id=#{0}")
	int deleteById(int id);
	
	@Results(value = {
			@Result(id = true, property = "id", column = "id"),
			@Result(property = "name", column = "name"),
			@Result(property = "list", column = "id",many = @Many(select="com.xujinshan.mapper.StudentMapper.selectByTid"))
			})
	@Select("select * from teacher")
	List<Teacher> selectTeacher();
}
