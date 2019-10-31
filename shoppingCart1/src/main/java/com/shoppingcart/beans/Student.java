package com.shoppingcart.beans;

import java.sql.Blob;

public class Student {
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
public void setPhoto(Blob photo) {
	this.photo = photo;
}
}
