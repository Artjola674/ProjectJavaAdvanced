package com.ikubinfo.primefaces.repository.admin;

import java.util.ArrayList;
import java.util.Date;
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

import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.repository.mapper.admin.OrderRowMapper;

@Repository
public class OrderRepository {
	
	Logger LOG = LoggerFactory.getLogger(OrderRepository.class);

	private static final String GET_ALL_ORDERS = "select order_id, total_price, delivered,order_date, client.first_name, client.last_name,client.address,client.phone_number\r\n" + 
			"from order_table inner join client on order_table.client_id = client.client_id\r\n" + 
			"where 1=1 ";
	private static final String GET_ORDER_QUANTITY = "select order_dish.quantity,dish.dish_name\r\n" + 
			"from order_dish inner join order_table on order_table.order_id = order_dish.order_id\r\n" + 
			"inner join dish on order_dish.dish_id = dish.dish_id where order_table.order_id = :orderId";
	private static final String DELIVER_ORDER = "update order_table set delivered = true where order_id = :orderId";
	private static final String GET_TOTAL_PRICE = "select sum(total_price) as total_price from order_table\r\n" + 
			"where delivered = true";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	public OrderRepository(DataSource dataSource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Order> getAllOrders(Boolean delivered,Date startDate,Date endDate) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("delivered", delivered);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		String queryString = GET_ALL_ORDERS;
		
		if (!Objects.isNull(delivered)) {
			queryString = queryString.concat(" and delivered = :delivered ");
		}
		if (!Objects.isNull(startDate)) {
			queryString = queryString.concat(" and order_date >= :startDate ");
		}
		if (!Objects.isNull(endDate)) {
			queryString = queryString.concat(" and order_date <= :endDate ");
		}
		
		List<Order> orders = new ArrayList<>();
		orders = namedParameterJdbcTemplate.query(queryString, params, new OrderRowMapper());
		
		for(Order order : orders) {
			List<String> toReturn = new ArrayList<>();
			Map<String, Object> parameter = new HashMap<>();
			parameter.put("orderId", order.getOrderId());
			toReturn = namedParameterJdbcTemplate.query(GET_ORDER_QUANTITY, parameter , (rs, rownum) -> {  
				return (rs.getString("dish_name").concat(" -> ")).concat(String.valueOf(rs.getInt("quantity"))); });
			StringBuilder strbul=new StringBuilder();
	        for(String str : toReturn)
	        {
	            strbul.append(str);
	            strbul.append("\n ");
	        }
	        String str=strbul.toString();
	        order.setQuantityAndName(str);
		}
		
		return orders;
	}

	public boolean deliver(int orderId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("orderId", orderId);
		
		int updatedCount = this.namedParameterJdbcTemplate.update(DELIVER_ORDER, namedParameters);
		return updatedCount > 0;
	}
	
public int getTotalPrice(Date startDate,Date endDate) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		String queryString = GET_ALL_ORDERS;
		
		if (!Objects.isNull(startDate)) {
			queryString = queryString.concat(" and order_date >= :startDate ");
		}
		if (!Objects.isNull(endDate)) {
			queryString = queryString.concat(" and order_date <= :endDate ");
		}
		
		return jdbcTemplate.queryForObject(queryString, Integer.class, params);
		
	}

	
}
