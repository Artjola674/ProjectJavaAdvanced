package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ikubinfo.primefaces.model.admin.Delivery;
import com.ikubinfo.primefaces.service.admin.DeliveryService;
import com.ikubinfo.primefaces.service.admin.OrderService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class DeliveryManagedBean implements Serializable{
	
	private static final long serialVersionUID = -9100934033459030204L;

	@ManagedProperty(value = "#{deliveryService}")
	private DeliveryService deliveryService;
	
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	
	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	private Delivery delivery;
	private List<Delivery> deliveries;
	private String status;
	private Boolean working;
	private int orderId;
	private int show;
	private boolean showChooseColumn;
	private boolean showDeleteColumn;
	private boolean showEditColumn;
	
	@PostConstruct
	public void init() {
		
		String showString =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show");
		show = Integer.parseInt(showString);
		delivery = new Delivery();
		deliveries = deliveryService.getAllDeliveries(status,working);
		if(show == 2) {
			showChooseColumn = false;
			showDeleteColumn = true;
			showEditColumn = true;
		}else {
			String idString =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
			orderId = Integer.parseInt(idString);
			showChooseColumn = true;
			showDeleteColumn = false;
			showEditColumn = false;
		}
	}
	
	public void search() {
		deliveries = deliveryService.getAllDeliveries(status,working);
		System.out.println(status);
		System.out.println(working);
	}
	
	public void delete() {
		if (deliveryService.delete(delivery.getDeliveryId())) {
			deliveries = deliveryService.getAllDeliveries(status,working);
			messages.showInfoMessage("Deleted");

		} else {
			messages.showInfoMessage("Something went wrong");
		}
	}
	
	public String send() {
		if(orderService.sendOrderToDelivery(delivery,orderId)) {
			messages.showInfoMessage("Order was sent successfully to delivery");
			delivery = new Delivery();
			return "allOrders.xhtml?show=2faces-redirect=true";
		}else {
			messages.showInfoMessage("Something went wrong");
			return "delivery.xhtml?show=1faces-redirect=true";
		}
	}
	
	public void save() {
		if(deliveryService.save(delivery)) {
			messages.showInfoMessage("Delivery was updated successfully");
			delivery = new Delivery();
		}else {
			messages.showInfoMessage("Something went wrong");
		}
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

	public boolean isShowChooseColumn() {
		return showChooseColumn;
	}

	public void setShowChooseColumn(boolean showChooseColumn) {
		this.showChooseColumn = showChooseColumn;
	}

	public boolean isShowDeleteColumn() {
		return showDeleteColumn;
	}

	public void setShowDeleteColumn(boolean showDeleteColumn) {
		this.showDeleteColumn = showDeleteColumn;
	}

	public boolean isShowEditColumn() {
		return showEditColumn;
	}

	public void setShowEditColumn(boolean showEditColumn) {
		this.showEditColumn = showEditColumn;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Boolean getWorking() {
		return working;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}
	
	
	
}
