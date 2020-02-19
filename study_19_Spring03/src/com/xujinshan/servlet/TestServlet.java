package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xujinshan.service.AirportService;
import com.xujinshan.service.impl.AirportServiceImpl;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AirportService airportService;
	@Override
	public void init() throws ServletException {
		// 对Service实例化
//		ApplicationContext ac = new ClassPathXmlApplicationContext("");
		// spring和web 整合后所有信息都存放在WebApplicationContext
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		airportService = ac.getBean("airportServiceImpl",AirportServiceImpl.class);
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", airportService.show());
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
