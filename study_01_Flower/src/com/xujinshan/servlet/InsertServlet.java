package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.Flower;
import com.xujinshan.service.FlowerService;
import com.xujinshan.service.impl.FlowerServiceImpl;
/**
 * Tomcat 7以上
 * web 3.0 以上版本才可以使用注解
 */
/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlowerService flowerService = new FlowerServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String production = req.getParameter("production");
		Flower flower = new Flower();
		flower.setName(name);
		flower.setPrice(Double.parseDouble(price));
		flower.setProduction(production);
		int index = flowerService.addFlower(flower);
		if(index >0) {
			// 重定向，防止表单重复提交
			resp.sendRedirect("show");
		}else {
			resp.sendRedirect("add.jsp");
		}
	}
}
