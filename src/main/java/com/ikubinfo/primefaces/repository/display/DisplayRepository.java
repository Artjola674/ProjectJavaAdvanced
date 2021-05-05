package com.ikubinfo.primefaces.repository.display;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ikubinfo.primefaces.model.display.dish;
import com.ikubinfo.primefaces.repository.mapper.display.displayrowmapper;

@Repository
@Component
public class DisplayRepository{
     
   Logger LOG = LoggerFactory.getLogger(DisplayRepository.class);
   
   private static final String GET_ALL_DISHES = "SELECT dish_id, dish_name, price, description, category  FROM public.dish where 1=1";
   private static final String GET_ALL_CATEGORIES = "Select distinct(category) from dish";
   
   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DisplayRepository(DataSource dataSource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public List<dish> getAllDishes(String category) {
		Map<String, Object> params = new HashMap<>();
		String queryString = GET_ALL_DISHES;
		params.put("category","%" + category + "%");
		
		if (!Objects.isNull(category) && !category.isEmpty()) {
			queryString = queryString.concat(" and  category like  :category");

		}
		
		System.out.print(queryString);	
		return namedParameterJdbcTemplate.query(queryString, params, new displayrowmapper());
	}
	public List<String> getAllCategories() {

		return jdbcTemplate.queryForList(GET_ALL_CATEGORIES, String.class);
	}


}

