package com.shoppingcart.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.shoppingcart.beans.DataSource;
import com.shoppingcart.beans.Tennis;

public class TennisDaoImpl implements TennisDao {
	 @Autowired
   JdbcTemplate jdbcTemp;
	 
	public List<Tennis> tennisList() {
		List<Tennis> list = jdbcTemp.query("SELECT * FROM student", new RowMapper<Tennis>() {

			public Tennis mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tennis tennis = new Tennis();

				tennis.setId(rs.getInt("id"));
				tennis.setName(rs.getString("name"));
				tennis.setAge(rs.getInt("age"));

				return tennis;
			}

			});

		return list;
	}
	

	public Blob getPhotoById(int id) {
		String query = "select photo from student where id=?";

		Blob photo = jdbcTemp.queryForObject(query, new Object[] { id }, Blob.class);

		return photo;
	}


}
