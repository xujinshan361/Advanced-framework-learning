package com.xujinshan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.Flower;
import com.xujinshan.service.FlowerService;
import com.xujinshan.service.impl.FlowerServiceImpl;

/**
 * 命名规范	
 * 		项目名：没有要求，不起中文
 * 		包：公司域名倒着写
 * 		数据访问层：dao,persist,mapper
 * 		实体：entity,model,bean,javabean,pojo
 * 		业务控制：service,biz
 * 		控制器：controller,servlet,action,web
 * 		过滤器：filter
 * 		异常：exception
 * 		监听器：listener
 * MVC开发模式:
 * 		M:Model 模型，实体，业务和dao
 * 		V:view 视图 JSP
 * 		C:Controller 控制器，servlet
 * 	
 * @author xujinshan361@163.com
 *
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlowerService flowerService = new FlowerServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Flower> list = flowerService.show();
		req.setAttribute("list", list);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
