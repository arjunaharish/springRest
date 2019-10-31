package com.shoppingcart.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shoppingcart.beans.LoginBean;

public class UserRoleMapper implements RowMapper<LoginBean> {

	public LoginBean mapRow(ResultSet resultSet, int i) throws SQLException {

		LoginBean loginBean = new LoginBean();
		loginBean.setUserName(resultSet.getString("username"));
		loginBean.setPassword(resultSet.getString("password"));
		return loginBean;
	}

	
}
