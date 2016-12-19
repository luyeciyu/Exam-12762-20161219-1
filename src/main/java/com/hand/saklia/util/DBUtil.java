package com.hand.saklia.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	
	private Connection conn;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/sakila";
	private String name = "root";
	private String password = "admin";
	
	public  Connection getConnection(){
		try {

			Class.forName(driver);
			conn=DriverManager.getConnection(url, name, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public List queryForList(String sql, Object[] obj) {
		List<Map> resultlist = new ArrayList();
		Connection connect =this.getConnection();
		resultlist=queryForList(sql,obj,connect);
		return resultlist;
	}
	
	public List queryForList(String sql) {
		return queryForList(sql,new Object[]{});
	}
	
	public List queryForList(String sql, Object[] obj, Connection conn) {
		List<Map> resultlist = new ArrayList();
		if(conn==null)
		{
			//未获取连接
			return resultlist;
		}
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(sql);
			//connect.setAutoCommit(true);
			if (obj != null)
				for (int i = 0; i < obj.length; i++)
					psmt.setObject((i + 1), obj[i]);
			rs = psmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while (rs.next()) {
				Map map = new HashMap();
				for (int i = 0; i < cols; i++) {
					map.put((String) (rsmd.getColumnLabel(i + 1)).toUpperCase(),
							(rs.getObject(i + 1)==null?null:rs.getObject(i + 1).toString()));
				}
				resultlist.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.quitConn(rs, psmt, conn);
		}
		return resultlist;
	}
	
	public boolean quitConn(ResultSet rs,PreparedStatement psmt,Connection conn){
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
	
//	public static PreparedStatement getPstmt(Connection conn, String sql){
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return pstmt;
//	}
//	
//	public static PreparedStatement getPstmt(String sql){
//		Connection conn = getConn();
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return pstmt;
//	}
//	
//	
//	public static Statement getStmt(Connection conn){
//		Statement stmt = null;
//		try {
//			stmt = conn.createStatement();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return stmt;
//	}
//	
//	
//	public static ResultSet executeQuery(Statement stmt,  String sql){
//		ResultSet rs = null;
//		try {
//			rs = stmt.executeQuery(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return rs;
//	}
//	
//	
//	public static void closeConn(Connection conn){
//		try {
//			if(conn != null){
//				conn.close();
//				conn = null;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void closeStmt(Statement pstmt){
//		try {
//			if(pstmt != null){
//				pstmt.close();
//				pstmt = null;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void closeRs(ResultSet rs){
//		try {
//			if(rs != null){
//				rs.close();
//				rs = null;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
}
