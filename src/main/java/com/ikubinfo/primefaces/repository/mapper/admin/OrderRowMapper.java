package com.ikubinfo.primefaces.repository.mapper.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.admin.Order;


public class OrderRowMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderId(rs.getInt("order_id"));
		order.setDelivered(rs.getBoolean("delivered"));
		order.setOrderDate(new Date(rs.getTimestamp("order_date").getTime()));
		order.setTotalPrice(rs.getDouble("total_price"));
		order.setClientAddress(rs.getString("address"));
		order.setClientName((rs.getString("first_name").concat(" ")).concat(rs.getString("last_name")));
		order.setClientPhoneNumber(rs.getString("phone_number"));
		return order;
	}

}
