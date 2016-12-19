package com.hand.saklia.service;

import java.util.Map;

import com.hand.saklia.dao.FilmActorDao;
import com.hand.saklia.dao.FilmCategoryDao;
import com.hand.saklia.dao.FilmDao;
import com.hand.saklia.dao.FilmInventoryDao;
import com.hand.saklia.dao.RenTalDao;

public class FilmService {
	private FilmDao filmDao = new FilmDao();
	private FilmActorDao filmActorDao = new FilmActorDao();
	private FilmCategoryDao filmCategoryDao = new FilmCategoryDao();
	private FilmInventoryDao filmInventoryDao = new FilmInventoryDao();
	private RenTalDao renTalDao = new RenTalDao();
	
	public Map findPage(Integer page, Integer rows){
		return filmDao.findPage(page, rows);
	}
	
	public boolean add(String title, Integer languageId, String description){
		
		try{
			filmDao.add(title, languageId, description);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(Integer id, String title, Integer languageId, String description){
		try{
			filmDao.update(id, title, languageId, description);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer id){
	
		try{
			
			renTalDao.deleteByInventoryId(id);
			filmInventoryDao.deleteByFilmId(id);
			filmCategoryDao.deleteByFilmId(id);
			filmActorDao.deleteByFilmId(id);
			filmDao.deleteById(id);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
