package com.ikubinfo.primefaces.repository.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.primefaces.shaded.commons.io.IOUtils;
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
	private static final String UPDATE_DISH = "update dish set  category = :category, dish_name = :dishName, "
			+ "description = :description, price = :price, picture = :picture where dish_id = :id";
	private static final String GET_ADMIN_ID = "select admin_id from admin where email = ? ";
	public static final String GET_CATEGORY = "select distinct category from dish where deleted = 'false' ";
	public static final String GET_DISH_NAME = "select distinct dish_name from dish where deleted = 'false' ";
	private static final String AVAILABILITY_UPDATE = "update dish set availability = :availability where dish_id = :dishId";
	private static final String DELETE_DISH = "update dish set deleted = 'true' where dish_id = :dishId";
	
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
	
	public List<String> getCategories(Boolean availability) {
		
		String queryString = GET_CATEGORY;
		
		if (!Objects.isNull(availability)) {
			queryString = queryString.concat(" and availability = ? ");
			return jdbcTemplate.queryForList(queryString, String.class, availability);
		}

		return jdbcTemplate.queryForList(queryString, String.class);
	}
	
	public List<String> getDishNames(String category, Boolean availability) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", "%" + category + "%");
		params.put("availability", availability);

		String queryString = GET_DISH_NAME;

		if (!Objects.isNull(category) && !category.isEmpty()) {
			queryString = queryString.concat(" and  category like :category ");
		}
		if (!Objects.isNull(availability)) {
			queryString = queryString.concat(" and availability = :availability ");
		}
		return namedParameterJdbcTemplate.query(queryString, params, (rs, rownum) -> {
	        
			return rs.getString("dish_name"); });
	}

	public List<Dish> getAllDishes(String category, String dishName, Boolean availability) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", "%" + category + "%");
		params.put("dishName", "%" + dishName + "%");
		params.put("availability", availability);

		String queryString = GET_ALL_DISHES;

		if (!Objects.isNull(category) && !category.isEmpty()) {
			queryString = queryString.concat(" and  category like  :category ");
		}
		if (!Objects.isNull(dishName) && !dishName.isEmpty()) {
			queryString = queryString.concat(" and  dish_name like  :dishName ");
		}
		if (!Objects.isNull(availability)) {
			queryString = queryString.concat(" and availability = :availability ");
		}
	
		return namedParameterJdbcTemplate.query(queryString, params, new DishRowMapper());
	}
	
	
	public void availability(int dishId, boolean availability) {
		Map<String, Object> params = new HashMap<>();
		params.put("dishId", dishId);
		params.put("availability", availability);
		
		namedParameterJdbcTemplate.update(AVAILABILITY_UPDATE, params);

	}

	public boolean delete(int dishId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("dishId", dishId);
		
		int updatedCount = this.namedParameterJdbcTemplate.update(DELETE_DISH, namedParameters);
		return updatedCount > 0;
	}

	public boolean save(Dish dish) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("category", dish.getCategory()).addValue("dishName", dish.getDishName()).addValue("price", dish.getPrice())
		.addValue("description", dish.getDescription()).addValue("picture", dish.getPicture()).addValue("id", dish.getDishId());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_DISH, namedParameters);

		return updatedCount > 0;
	}
	
	public void save(InputStream inputStream, File file) throws IOException {
		OutputStream output = new FileOutputStream(file);
		IOUtils.copy(inputStream, output);
	}

}
