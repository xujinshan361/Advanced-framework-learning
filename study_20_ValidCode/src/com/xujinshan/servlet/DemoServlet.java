package com.xujinshan.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 输出字符流
		PrintWriter out =resp.getWriter();
		// 获取响应流
		ServletOutputStream os = resp.getOutputStream();
		InputStream is = new FileInputStream(new File(getServletContext().getRealPath("img"),"a.png"));
		int index = -1;
		while((index = is.read())!=-1) {
			os.write(index);
		}
	}
	
}
