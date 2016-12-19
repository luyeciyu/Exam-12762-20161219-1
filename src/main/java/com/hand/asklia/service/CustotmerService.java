package com.hand.asklia.service;

import com.hand.saklia.dao.CustomerDao;

public class CustotmerService {
	private CustomerDao customerDao = new CustomerDao();
	public boolean login(String firstName){
		return customerDao.getByName(firstName);
	}
}
