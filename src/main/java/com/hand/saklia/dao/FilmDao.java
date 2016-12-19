package com.hand.saklia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hand.saklia.util.DBUtil;

public class FilmDao {
	private DBUtil dbUtil = new DBUtil();
	
	public Map findPage(Integer page, Integer rows){
		int first = (page-1)*rows;
		String sql = "select a.film_id, a.title, a.description, l.name"
					+ " from film a"
					+ " left join language l on a.language_id=l.language_id"
					+ " order by a.film_id limit ?, ? ";
		List<Map<String, Object>> lists = new ArrayList<>();
		lists = dbUtil.queryForList(sql, new Object[]{first, rows});
		
		String sqlCount = "select count(*) as count"
				+ " from film a"
				+ " left join language l on a.language_id=l.language_id";
				//+ " limit ?, ?";
		List<Map<String, Object>> count = new ArrayList<>();
		count = dbUtil.queryForList(sqlCount);
		int total = Integer.parseInt((String)count.get(0).get("COUNT")) ;
		
		Map<String, Object> result = new HashMap<>();
		result.put("rows", lists);
		result.put("total", total);
		return result;
	}
	
	public int getTotal(){
		String sql = "select count(*) as count"
					+ " from film a"
					+ " left join language l on a.language_id=l.language_id";
		List<Map<String, Object>> lists = new ArrayList<>();
		lists = dbUtil.queryForList(sql);
		int total = Integer.parseInt((String)lists.get(0).get("COUNT")) ;
		return total;
	}
	
	public void deleteById(Integer id) throws Exception{
		String sql = "delete from film where film_id=?";
		Connection conn = dbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.execute();
		dbUtil.quitConn(null, pstmt, conn);	
	}
	
	public void add(String title, Integer languageId, String description) throws Exception{
		String sql = "insert into film(film_id, title, language_id, description) "
				+ " values(null, ?, ?, ?)";
		Connection conn = dbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setInt(2, languageId);
		pstmt.setString(3, description);
		pstmt.execute();
		
		dbUtil.quitConn(null, pstmt, conn);
		
	}
	
	public void update(Integer id, String title, Integer languageId, String description) throws Exception{

		String sql = "update film set title=?, language_id=?, description=? "
				+ " where film_id=?";
		
		Connection conn = dbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setInt(2, languageId);
		pstmt.setString(3, description);
		pstmt.setInt(4, id);
		pstmt.execute();
		
		dbUtil.quitConn(null, pstmt, conn);
		
	}
	
	
}
