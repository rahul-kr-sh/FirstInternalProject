package com.MMT.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.MMT.bean.Admin;
import com.MMT.bean.User;

public interface AdminDao {
	public int insert(Admin admin) throws SQLException, ClassNotFoundException, IOException ;
	public Admin search(String  uid) throws SQLException, ClassNotFoundException, IOException ;
	public int delete (String uid) throws SQLException, ClassNotFoundException, IOException ;
	public int update(String uid, Admin admin) throws SQLException, ClassNotFoundException, IOException;
	
}
