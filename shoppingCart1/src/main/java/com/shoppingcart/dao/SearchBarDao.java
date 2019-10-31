package com.shoppingcart.dao;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SearchBarDao {
	 @Autowired
	JdbcTemplate searchBarDaotemplate; 
	
	public void setsearchBarDaotemplate(JdbcTemplate searchBarDaotemplate) {    
	    this.searchBarDaotemplate = searchBarDaotemplate;    
	}    
	
	public Blob getPhotoById(String name) {
		String query="select 'photo' from imagetable where photo=?";
		Blob photo = searchBarDaotemplate.queryForObject(query, new Object[] { name }, Blob.class);
		return photo;
	}
	
}
