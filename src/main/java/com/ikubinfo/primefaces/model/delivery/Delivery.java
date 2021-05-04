package com.ikubinfo.primefaces.model.delivery;

import java.util.Date;

public class Delivery {
	private int deliveryId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private String status;
	private boolean booleanStatus;
	private Date lastUpdate;
	private boolean working;
	
	public Delivery() {
		super();
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

	public boolean isBooleanStatus() {
		return booleanStatus;
	}

	public void setBooleanStatus(boolean booleanStatus) {
		this.booleanStatus = booleanStatus;
	}

	@Override
	public String toString() {
		return "Delivery [deliveryId=" + deliveryId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", booleanStatus=" + booleanStatus + ", lastUpdate=" + lastUpdate + ", working=" + working + "]";
	}

	
	
	
	
}
