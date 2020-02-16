package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xujinshan.pojo.Account;
import com.xujinshan.service.AccountService;
import com.xujinshan.service.impl.AccountServiceImpl;


@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accountService = new AccountServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Account accountOut = new Account();
		accountOut.setAccNo(req.getParameter("accountOutNo"));
		accountOut.setPassword(Integer.parseInt(req.getParameter("accountOutPassword")));
		accountOut.setBalance(Double.parseDouble(req.getParameter("accountOutBalance")));
		Account accountIn = new Account();
		accountIn.setAccNo(req.getParameter("accountInNo"));
		accountIn.setName(req.getParameter("accountInName"));
		
		int index = accountService.transfer(accountIn, accountOut);
		if(index==AccountService.SUCCESS) {
			resp.sendRedirect("/study_08_Bank/log.jsp");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("code",index);
			resp.sendRedirect("/study_08_Bank/error/error.jsp");
		}
		
	}
}
