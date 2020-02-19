package com.xujinshan.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.pojo.Student;

/**
 * MyBatis 实现多表查询
 * 		MyBatis 实现多表查询方式：
 * 			业务装配：	
 * 				对俩个表编写单表查询语句，在业务(service)把查询的俩个结果进行关联
 * 			使用Auto Mapping 特性，在实现俩表联合查询是通过别名完成映射
 * 			使用MyBatis的<resultMap>标签进行实现
 *		多表查询是，类中包含另一个类的对象的分类
 *			单个对象
 *			集合对象
 *		resultMap 标签
 *			<resultMap>标签写在mapper.xml 中由程序员控制SQL查询结果与实体类的映射关系
 *				默认MyBatis 使用Auto Mapping特性
 *			使用<resultMap>标签时，<select>标签不要写resultType属性，而是使用resultMap 属性引用<resultMap>标签
 *			示例：
 *				<mapper namespace="com.xujinshan.mapper.TeacherMapper">
 *					<resultMap type="Teacher" id="mymap">
 *					<!-- 主键使用id标签配置映射关系 -->
 *					<id column="id" property="id1"/>
 *					<!-- 其他列使用result标签配置映射关系 -->
 *					<result column="name" property="name1"/>
 *					</resultMap>
 *					<select id="selAll" resultMap="mymap">
 *						select * from teacher
 *					</select>	
 *				</mapper>
 *		
 *		使用resultMap实现关联单个对象(N+1方式)
 *			N+1查询方式，先查询出某个表的全部信息，根据这个表的信息查询出另一个表的信息
 *			与业务装配的区别
 *				在service里面写代码，由MyBatis完成装配
 *			实现步骤：
 *				在Student实现类中国包含一个Teacher对象
 *				在TeacherMapper中提供一个查询
 *				在StudentMapper中	
 *					<association>装配一个对象时使用
 *					property：对象在类中的属性名
 *					select ：通过哪个查询查询出这个对象的信息
 *					column：把当前表的哪个类的值作为参数传递给另一个查询
 *					注意：
 *						大前提使用N+1 方式时，如果列名和属性名相同，可以不配置，使用Auto mapping特性，但是mybatis 默认只会给列专配一次
 *				示例代码：
 *					<mapper namespace="com.xujinshan.mapper.StudentMapper">
 *						<resultMap type="student" id="stuMap">
 *							<id column="id" property="id"/>
 *							<result column="name" property="name"/>
 *							<result column="age" property="age"/>
 *							<result column="tid" property="tid"/>
 *							<!-- 如果关联一个对象 -->
 *							<association property="teacher" select="com.xujinshan.mapper.TeacherMapper.selById" column="tid"></association>
 *						</resultMap>
 *						<select id="selAll" resultMap="stuMap">
 *							select * from student
 *						</select>
 *					</mapper>
 *				简化后：
 *					<mapper namespace="com.xujinshan.mapper.StudentMapper">
 *						<resultMap type="student" id="stuMap">
 *							<result column="tid" property="tid"/>
 *							<!-- 如果关联一个对象 -->
 *							<association property="teacher" select="com.xujinshan.mapper.TeacherMapper.selById" column="tid"></association>
 *						</resultMap>
 *						<select id="selAll" resultMap="stuMap">
 *							select * from student
 *						</select>
 *					</mapper>
 *
 *		使用<resultMap>实现关联单个对象(联合查询方式)
 *			只需要编写一个SQL，在TeacherMapper中添加下面效果，
 *				<association/>只要装配一个对象就用这个标签
 *				此时把<association/>当小的<resultMap>看待
 *				javaType属性：<association/>专配完后返回一个什么类型的对象，取值是一个类(或类的别名)
 *			示例代码：
 *				<resultMap type="Teacher" id="mymap1">
 *					<id column="tid" property="id"/>
 *					<result column="tname" property="name"/>
 *					<collection property="list" ofType="Student" >
 *						<id column="sid" property="id"/>
 *						<result column="sname" property="name"/>
 *						<result column="age" property="age"/>
 *						<result column="tid" property="tid"/>
 *					</collection>
 *				</resultMap>
 *				<select id="selAll1" resultMap="mymap1">
 *					select t.id tid,t.name tname,s.id sid,s.name sname,age,tid from teacher t LEFT JOIN student s on t.id=s.tid;
 *				</select>
 *		
 *		N+1方式和联合查方式对比
 *			N+1:需求不确定
 *			联合查询：需求中却查询时俩个表一定都查询
 *		N+1 名称由来
 *			举例：学生中有3条数据，
 *			需求：查询所有学生信息及授课老师信息
 *			需要执行的SQL命令
 *				查询全部学生信息：select * from 学生
 *				执行3遍select * from 老师 where id =学生的外键
 *			使用多条SQL命令查询俩表数据时，如果希望把需要的数据都查询出来，需要执行N+1条SQL语句才能把所有数据从库中查询出来
 *			缺点：效率低
 *			优点：如果有时候不需要查询学生还同时查询老师，只需要执行一个select * from student
 *			适用场景：有时候需要查询学生同时查询老师，有时候只需要查询学生
 *			如何解决N+1查询带来的效率低的问题：
 *				默认带的前提：每次都是俩个查询
 *				适用俩表联合查询
 *		适用<resultMpa>查询关联集合对象(N+1)
 *			在Teacher中添加List<Student>
 *			在StudentMapper.xml 中添加通过tid查询
 *				<select id="selByTid" parameterType="int" resultType="student">
 *					select * from student where tid=#{0}
 *				</select>
 *			在TeacherMapper.xml 中添加查询全部
 *				<collection /> 当属性是集合类型时使用的标签
 *				<resultMap type="teacher" id="mymap">
 *					<id column="id" property="id"/>
 *					<result column="name" property="name"/>
 *					<collection property="list"
 *						select="com.bjsxt.mapper.StudentMapper.selByTid" column="id">
 *					</collection>
 *				</resultMap>
 * 				<select id="selAll" resultMap="mymap">
 *					select * from teacher
 *				</select>		
 *		使用<resultMap>实现加载集合数据(联合查询方式)
 *			在TeacherMapper中添加
 *				mybatis可以通过主键判断对象是否被加载过
 *				不需要担心创建重复Teacher
 *					<resultMap type="teacher" id="mymap1">
 *	 					<id column="tid" property="id"/>
 *						<result column="tname" property="name"/>
 *						<collection property="list" ofType="student" >
 *							<id column="sid" property="id"/>
 *							<result column="sname" property="name"/>
 *							<result column="age" property="age"/>
 *							<result column="tid" property="tid"/>
 *						</collection>
 *					</resultMap>
 *					<select id="selAll1" resultMap="mymap1">
 *						select t.id tid,t.name tname,s.id sid,s.name
 *						sname,age,tid from teacher t LEFT JOIN student s on
 *						t.id=s.tid;
 *					</select>
 *
 *		使用Auto Mapping结合别名实现多表查询
 *			只能使用多表联合查询方式
 *			要求：查询出的列和别名和属性相同
 *			实现方式
 *				"."在SQL是关键字符，俩侧添加反单引号
 *				<select id="selAll2" resultType="student">
 *					select t.id `teacher.id`,t.name `teacher.name`,s.id id,s.name name,age,tid
 *					 from student s LEFT JOIN teacher t on t.id=s.tid
 *				</select>
 *
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
//		List<Teacher> list = session.selectList("com.xujinshan.mapper.TeacherMapper.selAll");
//		List<Student> list = session.selectList("com.xujinshan.mapper.StudentMapper.selAll");
		List<Student> list = session.selectList("com.xujinshan.mapper.TeacherMapper.selAll1");
		System.out.println(list);
	}
}
