package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.PageInfo;
import com.xujinshan.service.PeopleService;
import com.xujinshan.service.impl.PeopleServiceImpl;

@WebServlet("/page")
public class ShowPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PeopleService peopleService = new PeopleServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 第一次访问的验证，如果没有传递参数，设置默认值
		String pageSizeStr = req.getParameter("pageSize");
		int pageSize =2;
		if(pageSizeStr!=null && pageSizeStr!="") {
			pageSize = Integer.parseInt(req.getParameter("pageSize"));
		}
		String pageNumberStr = req.getParameter("pageNumber");
		int pageNumber = 1;
		if(pageNumberStr!=null &&pageNumberStr!="") {
			pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
		}
		PageInfo pi = peopleService.showPage(pageSize, pageNumber);
		req.setAttribute("pageInfo", pi);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
