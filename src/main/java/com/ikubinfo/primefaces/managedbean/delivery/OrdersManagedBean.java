package com.ikubinfo.primefaces.managedbean.delivery;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ikubinfo.primefaces.managedbean.admin.WelcomeManagedBean;
import com.ikubinfo.primefaces.model.admin.Order;
import com.ikubinfo.primefaces.model.delivery.Delivery;
import com.ikubinfo.primefaces.service.admin.OrderService;
import com.ikubinfo.primefaces.service.delivery.DeliveryService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class OrdersManagedBean implements Serializable {

	private static final long serialVersionUID = 6487760767177114033L;

	@ManagedProperty(value = "#{deliveryService}")
	private DeliveryService deliveryService;

	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@ManagedProperty(value = "#{welcomeManagedBean}")
	private WelcomeManagedBean welcomeManagedBean;

	private List<Order> orders;
	private Date startDate;
	private Date endDate;
	private int show;
	private boolean showDeliveredColumn;
	private boolean showReturnedColumn;
	private Delivery delivery;
	private int orderId;
	private Boolean deliver;
	private Boolean returned;

	@PostConstruct
	public void init() {
		String showString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show");
		show = Integer.parseInt(showString);
		delivery = deliveryService.getDelivery(welcomeManagedBean.getEmail());
		if (show == 1) {
			deliver = false;
			returned = true;
			showDeliveredColumn = false;
			showReturnedColumn = false;
			orders = orderService.getAllOrdersOfADelivery(deliver, startDate, endDate, returned,
					delivery.getDeliveryId());
		} else if (show == 2) {
			showDeliveredColumn = true;
			showReturnedColumn = true;
			returned = false;
			deliver = false;
			orders = orderService.getAllOrdersOfADelivery(deliver, startDate, endDate, returned,
					delivery.getDeliveryId());
		} else if (show == 3) {
			deliver = true;
			returned = false;
			showDeliveredColumn = false;
			showReturnedColumn = false;
			orders = orderService.getAllOrdersOfADelivery(deliver, startDate, endDate, returned,
					delivery.getDeliveryId());
		}
	}

	public void deliver() {
		if (orderService.deliver(orderId)) {
			orders = orderService.getAllOrdersOfADelivery(deliver, startDate, endDate, returned,
					delivery.getDeliveryId());
			messages.showInfoMessage("Order was delivered successfully");
		}

	}

	public void returned() {
		if (orderService.returned(orderId)) {
			orders = orderService.getAllOrdersOfADelivery(deliver, startDate, endDate, returned,
					delivery.getDeliveryId());
			messages.showInfoMessage("Order was returned successfully");
		}
	}

	public void search() {
		orders = orderService.getAllOrdersOfADelivery(deliver, startDate, endDate, returned, delivery.getDeliveryId());
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

	public int getShow() {
		return show;
	}

	public void setShow(int show) {
		this.show = show;
	}

	public boolean isShowDeliveredColumn() {
		return showDeliveredColumn;
	}

	public void setShowDeliveredColumn(boolean showDeliveredColumn) {
		this.showDeliveredColumn = showDeliveredColumn;
	}

	public boolean isShowReturnedColumn() {
		return showReturnedColumn;
	}

	public void setShowReturnedColumn(boolean showReturnedColumn) {
		this.showReturnedColumn = showReturnedColumn;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Boolean getDeliver() {
		return deliver;
	}

	public void setDeliver(Boolean deliver) {
		this.deliver = deliver;
	}

	public Boolean getReturned() {
		return returned;
	}

	public void setReturned(Boolean returned) {
		this.returned = returned;
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public WelcomeManagedBean getWelcomeManagedBean() {
		return welcomeManagedBean;
	}

	public void setWelcomeManagedBean(WelcomeManagedBean welcomeManagedBean) {
		this.welcomeManagedBean = welcomeManagedBean;
	}

}
