package com.ikubinfo.primefaces.repository.mapper.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.admin.Dish;

public class DishRowMapper implements RowMapper<Dish>{

	@Override
	public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dish dish = new Dish();
		dish.setDishId(rs.getInt("dish_id"));
		dish.setCategory(rs.getString("category"));
		dish.setDishName(rs.getString("dish_name"));
		dish.setDescription(rs.getString("description"));
		dish.setPrice(rs.getDouble("price"));
		dish.setAvailability(rs.getBoolean("availability"));
		dish.setAdminName((rs.getString("first_name").concat(" ")).concat(rs.getString("last_name")));
		dish.setPicture(rs.getString("picture"));
		dish.setLastUpdate(new Date(rs.getTimestamp("last_update").getTime()));
		return dish;
	}

}
