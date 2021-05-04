package com.ikubinfo.primefaces.repository.admin;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ikubinfo.primefaces.model.admin.Admin;
import com.ikubinfo.primefaces.repository.mapper.admin.AdminRowMapper;

@Repository
public class AdminRepository {
	Logger LOG = LoggerFactory.getLogger(AdminRepository.class);

	private static final String GET_ADMIN_ID_BY_EMAIL = "select admin_id from admin where lower(email) like lower(?) ";
	private static final String GET_ADMIN_BY_ID = "select admin_id, first_name, last_name, email, password, last_update from admin where admin_id = ? ";
	private static final String UPDATE_ADMIN = "update admin set first_name = :firstName, last_name = :lastName, password = :password where admin_id = :adminId ";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public AdminRepository(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public int getAdminId(String email) {
		return jdbcTemplate.queryForObject(GET_ADMIN_ID_BY_EMAIL, Integer.class, email);
	}

	public Admin getAdmin(int id) {
		return jdbcTemplate.queryForObject(GET_ADMIN_BY_ID, new Object[] { id }, new AdminRowMapper());
	}

	public boolean save(Admin editAdmin) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstName", editAdmin.getFirstName()).addValue("lastName", editAdmin.getLastName())
				.addValue("password", editAdmin.getPassword()).addValue("adminId", editAdmin.getId());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_ADMIN, namedParameters);
		return updatedCount > 0;
	}
}
