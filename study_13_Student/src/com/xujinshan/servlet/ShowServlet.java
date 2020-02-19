package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.PageInfo;
import com.xujinshan.service.StudentService;
import com.xujinshan.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StudentService stuService = new StudentServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String sname = req.getParameter("sname");
		// 这种写法是将服务器发送过来的编码改成utf-8
		// 一般不修改服务器编码格式==》主要原因：一个服务器有多个项目，不适合修改服务器编码格式
//		if (sname != null && !sname.equals(""))
//			sname = new String(sname.getBytes("iso-8859-1"), "utf-8");
		String tname = req.getParameter("tname");
//		if (tname != null && !tname.equals(""))
//			tname = new String(tname.getBytes("iso-8859-1"), "utf-8");
		String pageSize = req.getParameter("pageSize");
		String pageNumber = req.getParameter("pageNumber");
		PageInfo pi = stuService.showPage(sname, tname, pageSize, pageNumber);
		System.out.println(pi);
		req.setAttribute("pageinfo", pi);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

}
