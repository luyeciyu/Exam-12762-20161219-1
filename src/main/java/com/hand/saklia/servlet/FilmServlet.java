package com.hand.saklia.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.asklia.service.FilmService;
import com.hand.saklia.dao.FilmDao;

import net.sf.json.JSONArray;

public class FilmServlet extends HttpServlet{

	private FilmService filmService = new FilmService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String func = req.getParameter("func");
		if("find".equals(func)){
			this.findPage(req, resp);
			return;
		}else if("delete".equals(func)){
			this.delete(req, resp);
			return;
		}else if("addFilm".equals(func)){
			this.addFilm(req, resp);
			return;
		}else if("modifyFilm".equals(func)){
			this.modifyFilm(req, resp);
			return;
		}
	}
	
	public void findPage(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(req.getParameter("page"));
		int rows = Integer.parseInt(req.getParameter("rows"));
		Map<String, Object> result =  filmService.findPage(page, rows);
		JSONArray jsonArray = JSONArray.fromObject(result);
		resp.getWriter().print(jsonArray);
	}
	
	public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("utf-8");
		int filmId = Integer.parseInt(req.getParameter("film_id"));
		boolean flag = filmService.delete(filmId);
		if(flag){
			resp.getWriter().print("删除成功");
		}else{
			resp.getWriter().print("删除失败");
		}
	}
	
	public void addFilm(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		int languageId = Integer.parseInt(req.getParameter("language")); 
		String description = req.getParameter("description");
		boolean flag = filmService.add(title, languageId, description);
		if(flag){
			resp.getWriter().print("添加成功");
		}else{
			resp.getWriter().print("添加失败");
		}
	}
	
	public void modifyFilm(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
		int languageId = Integer.parseInt(req.getParameter("language")); 
		String description = req.getParameter("description");
		int filmId = Integer.parseInt(req.getParameter("filmId")); 
		boolean flag = filmService.update(filmId, title, languageId, description);
		if(flag){
			resp.getWriter().print("编辑成功");
		}else{
			resp.getWriter().print("编辑失败");
		}
	}
	
}
