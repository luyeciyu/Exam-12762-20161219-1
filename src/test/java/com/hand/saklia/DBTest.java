package com.hand.saklia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hand.saklia.util.DBUtil;

public class DBTest {
	
	@Test
	public void test(){
//		Connection conn = DB.getConn();
//		String sql = "select * from customer";
//		PreparedStatement pstmt = DB.getPstmt(conn, sql);
//		ResultSet rs = null;
//		try {
//			 rs = pstmt.executeQuery();
//			 
//			 while(rs.next()){
//				 System.out.println(rs.getString("first_name"));
//			 }
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		DBUtil dbUtil = new DBUtil();
		String sql = "select * from customer";
		List<Map<String, Object>> list = dbUtil.queryForList(sql);
		for(Map<String, Object> map :list){
			System.out.println(map.get("FIRST_NAME"));
		}
	}
}
