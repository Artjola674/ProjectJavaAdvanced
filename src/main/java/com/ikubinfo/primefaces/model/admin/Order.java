package com.ikubinfo.primefaces.model.admin;

import java.util.Date;

public class Order {
	private int orderId;
	private double totalPrice;
	private Date orderDate;
	private boolean delivered;
	private String quantityAndName;
	private String clientName;
	private String clientAddress;
	private String clientPhoneNumber;
	
	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public String getQuantityAndName() {
		return quantityAndName;
	}

	public void setQuantityAndName(String quantityAndName) {
		this.quantityAndName = quantityAndName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}

	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientAddress == null) ? 0 : clientAddress.hashCode());
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + ((clientPhoneNumber == null) ? 0 : clientPhoneNumber.hashCode());
		result = prime * result + (delivered ? 1231 : 1237);
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((quantityAndName == null) ? 0 : quantityAndName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (clientAddress == null) {
			if (other.clientAddress != null)
				return false;
		} else if (!clientAddress.equals(other.clientAddress))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (clientPhoneNumber == null) {
			if (other.clientPhoneNumber != null)
				return false;
		} else if (!clientPhoneNumber.equals(other.clientPhoneNumber))
			return false;
		if (delivered != other.delivered)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId != other.orderId)
			return false;
		if (quantityAndName == null) {
			if (other.quantityAndName != null)
				return false;
		} else if (!quantityAndName.equals(other.quantityAndName))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", delivered="
				+ delivered + ", quantityAndName=" + quantityAndName + ", clientName=" + clientName + ", clientAddress="
				+ clientAddress + ", clientPhoneNumber=" + clientPhoneNumber + "]";
	}

	
	
	
}
