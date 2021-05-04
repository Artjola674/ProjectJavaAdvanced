package com.ikubinfo.primefaces.repository.mapper.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ikubinfo.primefaces.model.admin.Admin;

public class AdminRowMapper implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		admin.setId(rs.getInt("admin_id"));
		admin.setEmail(rs.getString("email"));
		admin.setFirstName(rs.getString("first_name"));
		admin.setLastName(rs.getString("last_name"));
		admin.setPassword(rs.getString("password"));
		admin.setLastUpdate(new Date(rs.getTimestamp("last_update").getTime()));
		return admin;
	}
}
