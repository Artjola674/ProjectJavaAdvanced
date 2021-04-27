package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.service.admin.OrderService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@SessionScoped
public class ShowAllOrdersManagedBean implements Serializable {

	private static final long serialVersionUID = -146883774165759901L;

	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	private List<Order> orders;
	private int orderId;
	private Date startDate;
	private Date endDate;
	private boolean showTotal;
	private boolean showDelivered;
	private Boolean deliver;
	private double total;

	@PostConstruct
	public void init() {
		showDelivered = true;
		deliver = false;
		showTotal = false;
		orders = orderService.getAllOrders(deliver, startDate, endDate);
	}

	public void showOnlyUnDeliveredOrders() {
		showDelivered = true;
		deliver = false;
		showTotal = false;
		orders = orderService.getAllOrders(deliver, startDate, endDate);
	}

	public void showOnlyDeliveredOrders() {
		showDelivered = false;
		showTotal = true;
		deliver = true;
		orders = orderService.getAllOrders(deliver, startDate, endDate);
		total = orderService.getTotalPrice(startDate, endDate);
	}

	public void viewAllOrders() {
		showDelivered = true;
		deliver = null;
		showTotal = false;
		orders = orderService.getAllOrders(deliver, startDate, endDate);
	}

	public void deliver() {
		if (orderService.deliver(orderId)) {
			orders = orderService.getAllOrders(deliver, startDate, endDate);
			messages.showInfoMessage("Order was delivered successfully");
		}

	}

	public void search() {
		orders = orderService.getAllOrders(deliver, startDate, endDate);
		total = orderService.getTotalPrice(startDate, endDate);
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isShowTotal() {
		return showTotal;
	}

	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}

	public boolean isShowDelivered() {
		return showDelivered;
	}

	public void setShowDelivered(boolean showDelivered) {
		this.showDelivered = showDelivered;
	}

	public Boolean getDeliver() {
		return deliver;
	}

	public void setDeliver(Boolean deliver) {
		this.deliver = deliver;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
