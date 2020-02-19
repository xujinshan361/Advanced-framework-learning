package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.Log;
import com.xujinshan.service.LogService;
import com.xujinshan.service.impl.LogServiceImpl;

/**
 * ThreadLocal 
 * 		线程容器，给线程保定一个Object内容，只要线程不变，可以随时取出
 * 		线程改变，无法取出
 * 		
 * 通过ThreadLocal，封装SQLSession对象
 * @author xujinshan361@163.com
 *
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet{
	private LogService logService = new LogServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Log log = new Log();
		log.setAccountIn(req.getParameter("accountIn"));
		log.setAccountOut(req.getParameter("accountOut"));
		log.setMoney(Double.parseDouble(req.getParameter("money")));
		int index = logService.ins(log);
		if(index>0){
			resp.sendRedirect("success.jsp");
		}else{
			resp.sendRedirect("error.jsp");
		}
	}
}
