package com.ikubinfo.primefaces.managedbean.delivery;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.managedbean.admin.WelcomeManagedBean;
import com.ikubinfo.primefaces.model.admin.Delivery;
import com.ikubinfo.primefaces.service.admin.DeliveryService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class DeliveryProfileManagedBean implements Serializable {

	private static final long serialVersionUID = 8283272265375496751L;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@ManagedProperty(value = "#{deliveryService}")
	private DeliveryService deliveryService;

	@ManagedProperty(value = "#{welcomeManagedBean}")
	private WelcomeManagedBean welcomeManagedBean;

	private Delivery delivery;

	@PostConstruct
	public void init() {
		delivery = deliveryService.getDelivery(welcomeManagedBean.getEmail());
	}

	public void addStatusToFree() {
		deliveryService.changeStatus(true, delivery.getEmail());
		delivery = deliveryService.getDelivery(welcomeManagedBean.getEmail());
	}

	public void addStatusToBusy() {
		deliveryService.changeStatus(false, delivery.getEmail());
		delivery = deliveryService.getDelivery(welcomeManagedBean.getEmail());
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
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
