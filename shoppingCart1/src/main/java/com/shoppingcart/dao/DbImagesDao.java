package com.shoppingcart.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public interface DbImagesDao {
	public InputStream getDbImages() throws SQLException, IOException;
	public static final String SQL_PROPERTY = "queries.properties";
	public static final String scheduleInstallQuery = "GetDbImages";
	public String getImagesSearchQuery();
}
