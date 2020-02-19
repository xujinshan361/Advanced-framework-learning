package com.xujinshan.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.mapper.StudentMapper;
import com.xujinshan.mapper.TeacherMapper;
import com.xujinshan.pojo.Student;
import com.xujinshan.pojo.Teacher;

/**MyBatis 注解
 * 		注解：为了简化配置文件
 * 		MyBatis 的注解简化了mapper.xml 文件
 * 			如果涉及动态SQL依然使用mapper.xml
 * 		mapper.xml 和注解可以共存
 * 		使用注解时，mybatis.xml 中<mappers>使用
 * 			<package/>
 * 			<mapper class="">
 * 		实现查询
 * 			@Select("select *from teacher")
 * 			List<Teacher> selectAll();
 * 		实现新增
 *			@Insert("insert into teacher values(default,#{name})")
 *			int insertTeacher (Teacher teacher);
 *	
 *		实现修改
 *			@Update("update teacher set name=#{name} where id=#{id}")
 *			int updateTeacher(Teacher teacher);
 *			
 *		实现删除
 *			@Delete("delete from teacher where id=#{0}")
 *			int deleteById(int id);
 *		使用注解实现<resultMap>功能
 *			以N+1举例
 *			在StudnetMapper接口中添加查询
 *				@Select("select * from student where tid=#{0}")
 *				List<Student> selectByTid(int tid);
 *			在TeacherMapper接口添加	
 *				@Results()		相当于<resultMap>
 *				@Result()		相当于<id/>或<result/>
 *					@Result(it=true)	相当于<id/>
 *				@Many()				相当于<collection/>
 *				@One()				相当于<association/>
 *		
 *			@Results(value = {
 *			@Result(id = true, property = "id", column = "id"),
 *			@Result(property = "name", column = "name"),
 *			@Result(property = "list", column = "id",many = @Many(select="com.xujinshan.mapper.StudentMapper.selectByTid"))
 *			})
 *			@Select("select * from teacher")
 *			List<Teacher> selectTeacher();
 *运行原理
 *		运行过程中涉及到的类
 *			Resources MyBatis 中的IO流工具类
 *				加载配置文件
 *			SqlSessionFactoryBuilder()构建器
 *				创建SqlSessionFactory 接口的实现类
 *			XMLConfigBuilder MyBatis 全局配置文件类容构建器
 *				负责读取流内容并转换为java代码
 *			Configuration 封装了全局配置文件所配置信息
 *				全局配置文件内容存放在Configuration
 *			DefaultSQLSessionFactory 是SqlSessionFactory接口的实现类
 *			Transaction 事务类
 *				没一个SqlSession都会带有一个Transaction对象
 *			TransactionFactory事务工厂
 *				负责生产Transaction
 *			Executor MyBatis 执行器
 *				负责执行SQL命令
 *				相当于JDBC中statement对象(或preparedStatemet或CallableStatement)
 *				默认的执行器SimpleExcutor
 *				批量操作 BatchExcutor
 *				通过openSession(参数控制)
 *			DefaultSqlSession是SqlSession 接口的实现
 *			ExceptionFactory  MyBatis中异常工厂
 *
 *流程图：
 *
 *Resources加载全局配置文件---》 实例化SqlSessionFactoryBuilder 构建器----》由XMLConfigBuilder 解析配置文件流--》把配置信息存放在Configuration ---》实例化SqlSessionFactory实现类DefaultSQLSessionFactory
 *																											 										|
 *																																				 	|
 *																																					|																			
 *	关闭《---事务提交《--------执行是否成功《---------实现CURD《--------创建SqlSession接口实现类DefaultSqlSession《-------创建执行器Excutor《-----------由TransactionFactory创建一个Transaction事务对象
 *							|					|																						|		|
 *							|					|																						|		|
 *							|					---------------------------------------------------------------------------------------》
 *							|------------------------------------------------------------------------------------------------------------------》
 *
 *
 *文字解释
 *		在 MyBatis 运行开始时需要先通过 Resources 加载全局配置文件.下面需要实例化 SqlSessionFactoryBuilder 构建器.帮助 SqlSessionFactory 接
 *		口实现类 DefaultSqlSessionFactory.在实例化 DefaultSqlSessionFactory 之前需要先创建 XmlConfigBuilder解析全局配置文件流,并把解析结果
 *		存放在 Configuration 中.之后把Configuratin 传递给 DefaultSqlSessionFactory.到此 SqlSessionFactory 工厂创建成功.
 *		由 SqlSessionFactory 工厂创建 SqlSession.每次创建 SqlSession 时,都需要由 TransactionFactory 创建 Transaction对象,同时还需要创建 SqlSession
 * 		的执行器 Excutor,最后实例化DefaultSqlSession,传递给 SqlSession 接口.根据项目需求使用 SqlSession 接口中的 API 完成具体的事务操作.如果事务执行失败,需要进行
 *  	rollback 回滚事务.如果事务执行成功提交给数据库.关闭 SqlSession
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		TeacherMapper tm = session.getMapper(TeacherMapper.class);
		// 测试查询全部
//		List<Teacher> list = tm.selectAll();
//		System.out.println(list);
		
		// 测试新增
//		Teacher teacher = new Teacher();
//		teacher.setName("测试");
//		int index = tm.insertTeacher(teacher);
		
		// 测试修改
//		Teacher teacher = new Teacher();
//		teacher.setId(3);
//		teacher.setName("李四");
//		int index = tm.updateTeacher(teacher);
		
//		StudentMapper sm = session.getMapper(StudentMapper.class);
//		List<Student> list = sm.selectAll();
//		
		List<Teacher> list = tm.selectTeacher();
		session.commit();
		session.close();
		System.out.println(list);
	}
}
