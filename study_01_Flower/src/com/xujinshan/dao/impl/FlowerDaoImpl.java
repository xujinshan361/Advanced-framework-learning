package com.xujinshan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.xujinshan.dao.FlowerDao;
import com.xujinshan.pojo.Flower;

/**
 * 数据访问层需要异常处理
 * @author xujinshan361@163.com
 *
 */
public class FlowerDaoImpl implements FlowerDao{

	
	
	@Override
	public List<Flower> selectAll() {
		// JDK1.7开始，后面泛型可以省略
		List<Flower> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			ps = (PreparedStatement) conn.prepareStatement("select * from flower");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Flower(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

	@Override
	public int insertFlower(Flower flower) {
		int index = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			ps = (PreparedStatement) conn.prepareStatement("insert into flower values(default,?,?,?)");
			ps.setObject(1, flower.getName());
			ps.setObject(2, flower.getPrice());
			ps.setObject(3, flower.getProduction());
			index = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return index;
	}
	
}
