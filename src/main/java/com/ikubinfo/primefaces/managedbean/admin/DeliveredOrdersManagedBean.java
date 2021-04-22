package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.service.admin.OrderService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class DeliveredOrdersManagedBean implements Serializable{

	private static final long serialVersionUID = 4697248188560039609L;
	
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	
	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	private List<Order> orders;
	private Date startDate;
	private Date endDate;
	
	@PostConstruct
	public void init() {
		orders = orderService.getAllOrders(true,startDate,endDate);
	}
	
	public void search() {
		orders = orderService.getAllOrders(true,startDate,endDate);
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
	
	

}
