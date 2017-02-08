package com.MMT.dao;

import java.sql.SQLException;
import java.util.List;

import com.MMT.bean.Admin;
import com.MMT.bean.User;

public interface AdminDao {
	public int insert(Admin admin) throws SQLException ;
	public Admin search(String  uid) throws SQLException ;
	public int delete (String uid) throws SQLException ;
	public int update(String uid, Admin admin) throws SQLException;
	
}
