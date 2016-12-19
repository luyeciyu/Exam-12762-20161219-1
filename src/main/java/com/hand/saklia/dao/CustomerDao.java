package com.hand.saklia.dao;

import java.util.List;
import java.util.Map;

import com.hand.saklia.util.DBUtil;

public class CustomerDao {
	
	private DBUtil dbUtil = new DBUtil(); 
	public boolean getByName(String firstName){
		String sql = "select count(*) as count from customer where first_name=?";
		List<Map<String, Object>> list =  dbUtil.queryForList(sql, new Object[]{firstName});
		if(list.size() <= 0){
			return false;
		}
		Map<String, Object> map = list.get(0);
		if(map.get("COUNT").equals("0")){
			return false;
		}
		
		return true;
	}
}
