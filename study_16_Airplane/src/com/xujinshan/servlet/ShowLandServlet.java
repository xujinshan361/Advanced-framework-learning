package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.service.AirportService;
import com.xujinshan.service.impl.AirportServiceImpl;


@WebServlet("/showland")
public class ShowLandServlet extends HttpServlet {
	private AirportService airportService = new AirportServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("landport", airportService.showLandPort());
		req.getRequestDispatcher("showairplane").forward(req, resp);
	}
}
