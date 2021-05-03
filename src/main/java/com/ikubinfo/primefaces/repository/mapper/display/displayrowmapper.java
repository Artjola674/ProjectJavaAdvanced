package com.ikubinfo.primefaces.repository.mapper.display;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.display.dish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class displayrowmapper implements RowMapper<dish>{

	@Override
	public dish mapRow(ResultSet rs, int rowNum) throws SQLException {
		dish dish =new dish();
		dish.setDish_id(rs.getInt("dish_id"));
		dish.setDish_name(rs.getString("dish_name"));
		dish.setPrice(rs.getInt("price"));
		dish.setDescription(rs.getString("description"));
		dish.setPicture(rs.getString("picture"));
		dish.setAvailability(rs.getBoolean("availability"));
		dish.setCategory(rs.getString("category"));
		dish.setDelete(rs.getBoolean("delete"));
		dish.setLast_update(rs.getDate("last_update"));
		return dish;
	}
	

}
