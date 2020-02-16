package com.xujinshan.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xujinshan.mapper.LogMapper;
import com.xujinshan.pojo.Log;

/**
 * MyBatis 接口绑定方案及多参数传递
 * 		作用：
 * 			实现创建一个接口后把mapper.xml 由mybatis生成接口的实现类，通过调用接口对象就可以获取mapper.xml 中编写 sql
 * 		后面mybatis和Spring整合时使用这个方案
 * 		
 * 		实现步骤：
 * 			创建一个接口
 * 				接口包名和接口名与mapper.xml 中<mapper> namespace 相同
 * 				接口中方法名和mapper.xml 标签的id属性相同
 * 			在mybatis.xml 中使用<package> 进行扫描接口和mapper.xml 
 * 		
 * 		代码实现步骤：
 * 			在mybatis.xml 中<mappers>下使用<package>
 * 				<mappers>
 * 					<package name="com.xujinshan.mapper"/>
 * 				</mappers>
 * 			在com.xujinshan.mapper 下新建接口
 * 				public interface LogMapper {
 *					List<Log> selectAll();
 *				}
 *			在com.xujinshan.mapper 下新建一个LogMapper.xml
 *				namespace 必须和接口全限定路径(包名+类名)一致
 *				id值必须和接口中方法名相同
 *				如果接口方法为多个参数，可以省略parameterType
 *
 *		多参数实现办法：
 *			在接口中声明方法
 *				List<Log> selectByAccountInAndAccountOut(String accountIn, String accountOut); 
 *			在mappe.xml 中添加
 *				#{}中使用0,1,2,3 或param1，param2
 *				<!-- 当参数时，不需要写parameterType -->
 *				<select id="selectByAccountInAndAccountOut" resultType="Log">
 *					select * from log where accountIn=#{0} and accountOut=#{1}
 *					<!-- select * from log where accountIn=#{param1} and accountOut=#{param2} -->
 *				</select>
 *			可以使用注解方式：
 *				在接口中声明方法
 *					List<Log> selectByAccountInAndAccountOut(@Param("accountIn")String accountIn, @Param("accountOut")String accountOut);
 *				在#{} 里面写@Param("内容")参数中的内容
 *				注意：导入的是ibatis包
 *动态SQL
 *		根据不同的条件需要执行不同的SQL命令，称为动态SQL
 *		MyBatis 中动态SQL在mapper.xml 中添加逻辑判断等
 *		
 *		if使用
 *	 		<select id="selectAccInAccOut" resultType="Log">
 *				select * from log where 1=1 
 *					OGNL表达式，直接写key或对象的属性,不需要添加任何特殊符号
 *				<if test="accountIn!=null and accountIn!=''">
 *					and accountIn=#{accountIn}
 *				</if>
 *				<if test="accountOut!=null and accountOut!=''">
 *					and accountOut=#{accountOut}
 *				</if>
 *			</select>
 *		
 *		<where>
 *			当编写where标签时，如果内容中第一个是and 去掉第一个and
 *			如果<where> 中有内容会生成where关键字，如果没有内容不生成where
 *			示例：比直接使用<if> 少写where 1=1
 *				<select id="selectAccInAccOut" resultType="Log">
 *					select * from log 
 *					<where>
 *							OGNL表达式，直接写key或对象的属性,不需要添加任何特殊符号 
 *						<if test="accountIn!=null and accountIn!=''">
 *							and accountIn=#{accountIn}
 *						</if>
 *						<if test="accountOut!=null and accountOut!=''">
 *							and accountOut=#{accountOut}
 *						</if>
 *					</where>
 *				</select>
 *
 *		<choose><when><otherwise>
 *			只有有一个成立，其他都不执行
 *			代码示例：
 *				如果accountIn和accountOut都不是null，或者不是“” 生成的SQL中只有 where accountIn=?
 *				<select id="selectAccInAccOut" resultType="Log">
 *					select * from log 
 *					<where>
 *						<choose>
 *							<when test="accountIn!=null and accountIn!=''">
 *								and accountIn=#{accountIn}
 *							</when>
 *							<when test="accountOut!=null and accountOut!=''">
 *								and accountOut=#{accountOut}
 *							</when>
 *						</choose>
 *					</where>
 *				</select>
 *
 *		<set> 用在修改SQL中set从句
 *			作用：		
 *				去掉最后一个逗号
 *				如果<set>里面有内容生成set关键字，没有就不生成
 *			示例
 *				id=#{id} 目的防止<set> 中没有内容，mybatis不生成set关键字，如果修改中没有set从句,SQL语法错误
 *				<update id="upd" parameterType="Log">
 *					update log 
 *					<set>
 *						id=#{id},
 *						<if test="accountIn!=null and accountIn!=''" >
 *							accountIn=#{accountIn},
 *						</if>
 *						<if test="accountOut!=null and accountOut!=''">
 *							and accountOut=#{accountOut},
 *						</if>
 *					</set>
 *					where accountIn=#{accountIn}
 *				</update>	
 *
 *		Trim
 *			prefix  在前面添加内容
 *			prefixOverrides 去掉前面内容
 *			suffix 在后面添加内容
 *			suffixOverrides 去掉后面内容
 *			注意：执行顺序是先去掉内容后添加内容
 *			示例代码：
 *				<update id="upd" parameterType="Log">
 *					update log
 *					<trim prefix="set" suffixOverrides=",">
 *						a=a,
 *					</trim>
 *					where id=100
 *				</update>
 *		<bind>
 *			作用：
 *				给参数重新赋值
 *			场景：	
 *				模糊查询
 *				在原内容前或后添加内容
 *			示例代码：
 *				<select id="selectByLog" parameterType="Log" resultType="Log">
 *					<bind name="money" value="'$'+money/>
 *						#{money}           <!--相当于java的字符串拼接 ==》 "$"+money-->
 *					</select>
 *
 *		<foreach>
 *			循环参数内容，还具备在内容的前后添加内容，还具备添加分隔符功能
 *			适用场景：
 *				in查询，批量新增(mybatis 中foreach 效率比较低)
 *			如果希望批量新增，SQL命令
 *				insert into log values(default,1,2,3),(default,2,3,4),(default,3,4,5)
 *			oppenSession()必须指定
 *				底层JDBC的PreparedStatement.addBatch();
 *				factory.openSession(ExecutorType.BATCH);
 *			示例：
 *				collection=""	要遍历的集合
 *				item 			迭代变量 #{迭代变量名}获取内容
 *				open		循环后左侧添加的内容
 *				close		循环后右侧添加的内容
 *				separator	每次循环时，元素之间的分割符
 *				<insert id="ins" parameterType="list">
 *					insert into log values
 *					<trim suffixOverrides=",">
 *						<foreach collection="list" item="log" >
 *							(default,#{log},2,2),
 *						</foreach>
 *					</trim>
 *				</insert>
 *
 *		<sql>和<include>
 *			某些SQL片段如果希望复用，可以使用<sql>定义这个片段
 *				<sql id="mysql">
 *					id,accoutIn,accountOut,money
 *				</sql>
 * 			在select delect update insert 中使用include引用
 * 			<select id="">
 *				select<include refid="mysql"></include>
 *				from log
 *			</select>
 *
 *		
 * @author xujinshan361@163.com
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		/**
		 * 接口，为什么嫩实例化？
		 * 
		 * 需要给接口一个实例化对象
		 * 
		 * 使用JDK的动态代理设计模式
		 * 面向接口的代理设计模式(必须有接口)
		 */
//		LogMapper logMapper= session.getMapper(LogMapper.class);
//		List<Log> list = logMapper.selectAll();
//		
//		for(Log log:list) {
//			System.out.println(log);
//		}
//		
//		List<Log> list2 =logMapper.selectByAccountInAndAccountOut("3", "1");
		
//		Scanner scanner =new Scanner(System.in);
//		System.out.println("请输入转账账号：");
//		String accountOut = scanner.nextLine();
//		System.out.println("请输入转入账号：");
//		String accountIn = scanner.nextLine();
		
		LogMapper logMapper = session.getMapper(LogMapper.class);
//		List<Log> list = logMapper.selectAccInAccOut(accountIn, accountOut);
		
//		Log log = new Log();
//		log.setId(1);
//		log.setAccountIn(accountIn);
//		log.setAccountOut(accountOut);
//		int index = logMapper.upd(log);
		
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		logMapper.selectIn(list);
		
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < 10000; i++) {
			list.add(i);
		}
		logMapper.ins(list);
		session.commit();
		session.close();
//		System.out.println(index);
	}
}
