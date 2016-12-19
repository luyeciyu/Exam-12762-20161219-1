package com.hand.saklia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.hand.saklia.util.DBUtil;

public class RenTalDao {
	private DBUtil dbUtil = new DBUtil();
	public void deleteByInventoryId(Integer filmId) throws Exception{
		String sql = "delete from rental where inventory_id in("
				+ "select inventory_id from inventory where film_id=? )";
		Connection conn = dbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, filmId);
		pstmt.execute();
		
		dbUtil.quitConn(null, pstmt, conn);
		
	
	}
}
