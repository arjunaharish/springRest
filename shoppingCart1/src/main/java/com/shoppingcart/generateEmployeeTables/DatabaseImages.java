package com.shoppingcart.generateEmployeeTables;

import java.sql.Blob;

public class DatabaseImages {
	private String name;    
	private Blob photo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob blob) {
		this.photo = blob;
	}
	@Override
	public String toString() {
		return this.getName()+this.getPhoto();
		
	}
}
