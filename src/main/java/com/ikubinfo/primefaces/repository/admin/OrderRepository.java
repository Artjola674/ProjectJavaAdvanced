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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ikubinfo.primefaces.model.admin.Delivery;
import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.repository.mapper.admin.OrderRowMapper;

@Repository
public class OrderRepository {

	Logger LOG = LoggerFactory.getLogger(OrderRepository.class);

	private static final String GET_ALL_ORDERS = "select order_id, total_price, delivered,order_date, sent,returned,delivered, client.first_name, client.last_name,client.address,client.phone_number"
			+ " from order_table inner join client on order_table.client_id = client.client_id where 1=1 ";
	private static final String GET_ORDER_QUANTITY = "select order_dish.quantity,dish.dish_name\r\n"
			+ "from order_dish inner join order_table on order_table.order_id = order_dish.order_id\r\n"
			+ "inner join dish on order_dish.dish_id = dish.dish_id where order_table.order_id = :orderId";
	private static final String GET_ORDER_DELIVERY = "select delivery.first_name ,delivery.last_name, "
			+ "delivery.phone_number from delivery inner join order_table on delivery.delivery_id = order_table.delivery_id where order_table.order_id = ? ";
	private static final String DELIVER_ORDER = "update order_table set delivered = true where order_id = :orderId";
	private static final String RETURNED_ORDER = "update order_table set returned = true where order_id = :orderId";
	private static final String GET_TOTAL_PRICE = "select sum(total_price) as total from order_table \r\n" + 
			"where delivered = true and order_date >= ? and order_date <= ? ";
	public static final String SEND_ORDER_TO_DELIVERY = "update order_table set delivery_id = :deliveryId where order_id = :orderId ";
	public static final String UPDATE_SEND = "update order_table set sent = true where order_id = :orderId ";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public OrderRepository(DataSource dataSource) {
		super();
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Order> getAllOrders(Boolean delivered, Date startDate, Date endDate,Boolean sent,Boolean returned) {

		Map<String, Object> params = new HashMap<>();
		params.put("delivered", delivered);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("sent", sent);
		params.put("returned", returned);

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
		if (!Objects.isNull(sent)) {
			queryString = queryString.concat(" and sent = :sent ");
		}
		if (!Objects.isNull(returned)) {
			queryString = queryString.concat(" and returned = :returned ");
		}
		

		return namedParameterJdbcTemplate.query(queryString, params, new OrderRowMapper());

	}
	
	
	public List<Order> getAllOrdersOfADelivery(Boolean delivered, Date startDate, Date endDate,Boolean returned,int deliveryId) {

		Map<String, Object> params = new HashMap<>();
		params.put("delivered", delivered);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("returned", returned);
		params.put("deliveryId", deliveryId);

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
		if (!Objects.isNull(returned)) {
			queryString = queryString.concat(" and returned = :returned ");
		}
		if (!Objects.isNull(deliveryId)) {
			queryString = queryString.concat(" and delivery_id = :deliveryId ");
		}

		return namedParameterJdbcTemplate.query(queryString, params, new OrderRowMapper());

	}
	
	public void getOrderQuantity(Order order) {
		List<String> toReturn = new ArrayList<>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("orderId", order.getOrderId());
		toReturn = namedParameterJdbcTemplate.query(GET_ORDER_QUANTITY, parameter, (rs, rownum) -> {
			return (rs.getString("dish_name").concat(" -> ")).concat(String.valueOf(rs.getInt("quantity")));
		});
		StringBuilder strbul = new StringBuilder();
		for (String str : toReturn) {
			strbul.append(str);
			strbul.append("\n ");
		}
		String str = strbul.toString();
		order.setQuantityAndName(str);
	}

	public boolean deliver(int orderId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("orderId", orderId);

		int updatedCount = this.namedParameterJdbcTemplate.update(DELIVER_ORDER, namedParameters);
		return updatedCount > 0;
	}

	public double getTotalPrice(Date startDate, Date endDate) {
		return jdbcTemplate.queryForObject(GET_TOTAL_PRICE, new Object[] {startDate, endDate}, (rs, rownum) -> {        
			return rs.getDouble("total"); }); 
	}

	public boolean returned(int orderId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("orderId", orderId);

		int updatedCount = this.namedParameterJdbcTemplate.update(RETURNED_ORDER, namedParameters);
		return updatedCount > 0;
	}
	
	public boolean sendOrderToDelivery(Delivery delivery, int orderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("deliveryId", delivery.getDeliveryId());
		params.put("orderId", orderId);

		int updatedCount = this.namedParameterJdbcTemplate.update(SEND_ORDER_TO_DELIVERY, params);
		return updatedCount > 0;
	}

	public boolean updateSend(int orderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_SEND, params);
		return updatedCount > 0;
	}
	
	public void getOrderDelivery(Order order) {
		String toReturn = jdbcTemplate.queryForObject(GET_ORDER_DELIVERY, new Object[] {order.getOrderId()}, (rs, rownum) -> {
			return ((((rs.getString("first_name").concat(" ")).concat(rs.getString("last_name"))).concat("\n")).concat(rs.getString("phone_number")));
		});
		
		order.setDeliveryDetail(toReturn);
	}


}