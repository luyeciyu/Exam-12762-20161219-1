package com.hand.saklia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.hand.saklia.util.DBUtil;

public class FilmCategoryDao {
	private DBUtil dbUtil = new DBUtil();
	public void deleteByFilmId(Integer id) throws Exception{
		String sql = "delete from film_category where film_id=?";
		Connection conn = dbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.execute();
		
		dbUtil.quitConn(null, pstmt, conn);
		
	
	}
}
