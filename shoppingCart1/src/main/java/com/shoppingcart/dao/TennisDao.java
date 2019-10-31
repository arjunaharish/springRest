package com.shoppingcart.dao;

import java.sql.Blob;
import java.util.List;

import com.shoppingcart.beans.Tennis;


public interface TennisDao {
public List<Tennis> tennisList();
	
	public Blob getPhotoById(int id);
}
