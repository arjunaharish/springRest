package com.shoppingcart.beans;


public class LoginBean {
private String userName;
private String password;

public void setUserName(String userName) {
	this.userName=userName;
}
public String getUserName() {
	return userName;
}

public void setPassword(String password) {
	this.password=password;
}
public String getPassword() {
	return password;
}


@Override
public String toString() {
	return "LoginBean{" + "username=" + userName + ", age=" + password + "}";
}

}
