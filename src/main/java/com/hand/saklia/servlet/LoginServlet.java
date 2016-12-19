package com.hand.saklia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.asklia.service.CustotmerService;
import com.hand.saklia.dao.CustomerDao;

public class LoginServlet extends HttpServlet{
	
	private CustotmerService custotmerService = new CustotmerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String firstName = req.getParameter("firstName");
		
		String loginFlag = (String)req.getSession().getAttribute("loginFlag");
		if(loginFlag != null && loginFlag.equals("true")){
			resp.sendRedirect("jsp/filmInfo.jsp");
			return;
		}
		boolean flag = custotmerService.login(firstName);
		if(flag != true){
			//req.setAttribute("loginError", "用户名错误！");
			req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
			return;
		}
		req.getSession().setAttribute("loginFlag", "true");
		resp.sendRedirect("jsp/filmInfo.jsp");
	}
	
}
