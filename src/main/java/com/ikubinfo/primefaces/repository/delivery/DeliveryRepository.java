package com.ikubinfo.primefaces.repository.delivery;

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

import com.ikubinfo.primefaces.model.delivery.Delivery;
import com.ikubinfo.primefaces.repository.mapper.delivery.DeliveryRowMapper;

@Repository
public class DeliveryRepository {

	Logger LOG = LoggerFactory.getLogger(DeliveryRepository.class);

	private static final String GET_DELIVERY_BY_EMAIL = "select delivery_id, first_name, last_name, email, password, status,working, phone_number,last_update from delivery where deleted = false and lower(email) like lower(?) ";
	private static final String GET_ALL_DELIVERIES = "select delivery_id, first_name, last_name, email, password, status,working, phone_number, last_update from delivery where deleted = false ";
	private static final String UPDATE_DELIVERY = "update delivery set first_name = :firstName, last_name = :lastName, password = :password, email = :email, "
			+ " phone_number = :phoneNumber  where delivery_id = :deliveryId ";
	private static final String UPDATE_STATUS = "update delivery set status = :status where lower(email) like lower(:email) ";
	private static final String UPDATE_WORKING = "update delivery set working = :working where lower(email) like lower(:email) ";
	private static final String INSERT_DELIVERY = "insert into delivery (first_name, last_name, email, password, phone_number) values(:firstName,:lastName,:email,:password,:phoneNumber)";
	private static final String DELETE_DELIVERY = "update delivery set deleted = 'true' where delivery_id = :deliveryId";
	private static final String GET_DELIVERIES_EMAIL = "select email from delivery where deleted = false ";

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DeliveryRepository(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Delivery getDelivery(String email) {
		return jdbcTemplate.queryForObject(GET_DELIVERY_BY_EMAIL, new Object[] { email }, new DeliveryRowMapper());
	}

	public boolean save(Delivery delivery) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstName", delivery.getFirstName()).addValue("lastName", delivery.getLastName())
				.addValue("password", delivery.getPassword()).addValue("email",delivery.getEmail())
				.addValue("phoneNumber", delivery.getPhoneNumber()).addValue("deliveryId", delivery.getDeliveryId());

		int updatedCount = this.namedParameterJdbcTemplate.update(UPDATE_DELIVERY, namedParameters);
		return updatedCount > 0;
	}

	public void changeStatus(boolean status, String email) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("email", email);

		this.namedParameterJdbcTemplate.update(UPDATE_STATUS, params);

	}

	public void changeWorkingStatus(boolean working, String email) {
		Map<String, Object> params = new HashMap<>();
		params.put("working", working);
		params.put("email", email);

		this.namedParameterJdbcTemplate.update(UPDATE_WORKING, params);

	}

	public boolean insert(Delivery delivery) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstName", delivery.getFirstName()).addValue("lastName", delivery.getLastName())
				.addValue("email", delivery.getEmail()).addValue("password", delivery.getPassword())
				.addValue("phoneNumber", delivery.getPhoneNumber());
		int updatedCount = this.namedParameterJdbcTemplate.update(INSERT_DELIVERY, namedParameters);
		return updatedCount > 0;
	}

	public List<Delivery> getAllDeliveries(Boolean status, Boolean working) {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("working", working);

		String queryString = GET_ALL_DELIVERIES;

		if (!Objects.isNull(status)) {
			queryString = queryString.concat(" and  status = :status ");
		}
		if (!Objects.isNull(working)) {
			queryString = queryString.concat(" and  working =  :working ");
		}

		return namedParameterJdbcTemplate.query(queryString, params, new DeliveryRowMapper());
	}

	public boolean delete(int deliveryId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("deliveryId", deliveryId);

		int updatedCount = this.namedParameterJdbcTemplate.update(DELETE_DELIVERY, namedParameters);
		return updatedCount > 0;
	}

	public List<String> getDeliveriesEmail() {
		return jdbcTemplate.queryForList(GET_DELIVERIES_EMAIL, String.class);
	}

}
