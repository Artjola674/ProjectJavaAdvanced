package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.service.admin.OrderService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class ShowAllOrdersManagedBean implements Serializable {

	private static final long serialVersionUID = -146883774165759901L;

	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	private List<Order> orders;
	private int show;
	private int orderId;
	private Date startDate;
	private Date endDate;
	private boolean showTotal;
	private boolean showSend;
	private boolean showDeliveryColumn;
	private Boolean deliver;
	private double total;
	private Boolean sent;
	private Boolean returned;
	

	@PostConstruct
	public void init() {
		String showString =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show");
		show = Integer.parseInt(showString);
		if(show == 1){
			showSend = false;
			showDeliveryColumn = true;
			deliver = null;
			showTotal = false;
			sent = true;
			returned = true;
			System.out.println("1");
			orders = orderService.getAllOrders(deliver, startDate, endDate,sent,returned);
			for(Order order:orders) {
				System.out.println("1");
				System.out.println(order);
			}
		}else if(show == 2){
			showDeliveryColumn = false;
			returned = false;
			showSend = true;
			deliver = false;
			showTotal = false;
			sent = false;
			orders = orderService.getAllOrders(deliver, startDate, endDate,sent,returned);
			for(Order order:orders) {
				System.out.println("2");
				System.out.println(order);
			}
		}else if(show== 3){
			showDeliveryColumn = true;
			showSend = false;
			showTotal = true;
			deliver = true;
			sent = true;
			returned = false;
			orders = orderService.getAllOrders(deliver, startDate, endDate,sent,returned);
			total = orderService.getTotalPrice(startDate, endDate);
			for(Order order:orders) {
				System.out.println("3");
				System.out.println(order);
			}
		}
	}

	public void search() {
		orders = orderService.getAllOrders(deliver, startDate, endDate,sent,returned);
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


	public int getShow() {
		return show;
	}


	public void setShow(int show) {
		this.show = show;
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


	public boolean isShowSend() {
		return showSend;
	}


	public void setShowSend(boolean showSend) {
		this.showSend = showSend;
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


	public Boolean getSent() {
		return sent;
	}


	public void setSent(Boolean sent) {
		this.sent = sent;
	}


	public Boolean getReturned() {
		return returned;
	}


	public void setReturned(Boolean returned) {
		this.returned = returned;
	}

	public boolean isShowDeliveryColumn() {
		return showDeliveryColumn;
	}

	public void setShowDeliveryColumn(boolean showDeliveryColumn) {
		this.showDeliveryColumn = showDeliveryColumn;
	}

	
	

}
