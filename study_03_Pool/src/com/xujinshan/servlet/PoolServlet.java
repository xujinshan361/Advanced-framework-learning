package com.xujinshan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * TODO 有bug，需要调整
 * 
 * @author  xujinshan361@163.com
 * 
 */
@WebServlet("/pool")
public class PoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Context cxt = new InitialContext();
			DataSource ds = (DataSource)cxt.lookup("java:comp/env/test");
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from flower");
			ResultSet rs = ps.executeQuery();
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print("succes");
			while(rs.next()) {
				out.print(rs.getInt(1)+"$nbsp$nbsp"+rs.getString(1));
			}
			
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
