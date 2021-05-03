package com.ikubinfo.primefaces.repository.mapper.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.admin.Delivery;


public class DeliveryRowMapper implements RowMapper<Delivery>{

	@Override
	public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException {
		Delivery delivery = new Delivery();
		delivery.setDeliveryId(rs.getInt("delivery_id"));
		delivery.setEmail(rs.getString("email"));
		delivery.setFirstName(rs.getString("first_name"));
		delivery.setLastName(rs.getString("last_name"));
		delivery.setPassword(rs.getString("password"));
		delivery.setPhoneNumber(rs.getString("phone_number"));
		delivery.setLastUpdate(new Date(rs.getTimestamp("last_update").getTime()));
		if(rs.getBoolean("status") == true){
			delivery.setStatus("Free");
		}else if(rs.getBoolean("status") == false){
			delivery.setStatus("Busy");
		}
		return delivery;
	}

}
