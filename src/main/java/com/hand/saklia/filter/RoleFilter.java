package com.hand.saklia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleFilter implements Filter {

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		String page = req.getServletPath();
		if(page.equalsIgnoreCase("/jsp/login.jsp") || page.equalsIgnoreCase("/index.jsp"))
		{
			chain.doFilter(request, response);
			return;
		}
		String loginFlag = (String)req.getSession().getAttribute("loginFlag");
		if(loginFlag == null || loginFlag.equals("false")){
			res.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
