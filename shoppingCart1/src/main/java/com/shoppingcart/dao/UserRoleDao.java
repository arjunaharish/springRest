package com.shoppingcart.dao;

import java.io.IOException;
import java.util.List;

import com.shoppingcart.beans.LoginBean;

public interface UserRoleDao {
String getRole(String userName, String password) throws IOException;
public static final String SQL_PROPERTY = "queries.properties";
public static final String scheduleInstallQuery = "UserRoleQuery";
public String getRoleSearchQuery(String userName, String password);
public LoginBean getRoleId(String userName, String password);
}
