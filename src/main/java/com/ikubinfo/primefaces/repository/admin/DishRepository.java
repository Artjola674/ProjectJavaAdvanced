package com.ikubinfo.primefaces.repository.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ikubinfo.primefaces.model.admin.Dish;
import com.ikubinfo.primefaces.repository.mapper.admin.DishRowMapper;

@Repository
public class DishRepository {
	
	Logger LOG = LoggerFactory.getLogger(DishRepository.class);
	
	private static final String INSERT_DISH = "insert into dish (category,dish_name,description, price,admin_id,picture) values(:category,:dishName,:description,:price,:adminId,:picture)";
	private static final String GET_ALL_DISHES = "select dish_id,category,dish_name,description,price,picture,availability,dish.last_update, admin.first_name, admin.last_name \r\n" + 
			"from dish inner join admin\r\n" + 
			"on dish.admin_id = admin.admin_id\r\n" + 
			"where dish.deleted = 'false'";
	private static final String GET_ADMIN_ID = "select admin_id from admin where email = ? ";
	public static final String GET_CATEGORY = "select distinct category from dish order by category";
	public static final String GET_DISH_NAME = "select distinct dish_name from dish where deleted = 'false' ";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	
	public DishRepository(DataSource dataSource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getAdminId(String email) {
		return jdbcTemplate.queryForObject(GET_ADMIN_ID, Integer.class, email);
	}

	public boolean insertDish(Dish dish, int adminId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("category", dish.getCategory()).addValue("dishName", dish.getDishName()).addValue("description", dish.getDescription())
		.addValue("price", dish.getPrice()).addValue("adminId", adminId).addValue("picture", dish.getPicture());
		int updatedCount = namedParameterJdbcTemplate.update(INSERT_DISH, namedParameters);
		return updatedCount > 0;
	}
	
	public List<String> getCategories() {
		return jdbcTemplate.queryForList(GET_CATEGORY, String.class);
	}
	
	public List<String> getDishNames(String category) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", "%" + category + "%");

		String queryString = GET_DISH_NAME;

		if (!Objects.isNull(category) && !category.isEmpty()) {
			queryString = queryString.concat(" and  category like  :category ");
		}
		
		return jdbcTemplate.query(queryString, (rs, rownum) -> {
	        
			return rs.getString("dish_name"); });
	}

	public List<Dish> getAllDishes(String category, String dishName) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", "%" + category + "%");
		params.put("dishName", "%" + dishName + "%");

		String queryString = GET_ALL_DISHES;

		if (!Objects.isNull(category) && !category.isEmpty()) {
			queryString = queryString.concat(" and  category like  :category ");
		}
		if (!Objects.isNull(dishName) && !dishName.isEmpty()) {
			queryString = queryString.concat(" and  dish_name like  :dishName ");
		}
		return namedParameterJdbcTemplate.query(queryString, params, new DishRowMapper());
	}

}
