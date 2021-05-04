package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.model.admin.Delivery;
import com.ikubinfo.primefaces.service.admin.DeliveryService;
import com.ikubinfo.primefaces.util.Messages;

@ViewScoped
@ManagedBean
public class AddDeliveryManagedBean implements Serializable{

	private static final long serialVersionUID = 8159601054479302670L;

	@ManagedProperty(value = "#{deliveryService}")
	private DeliveryService deliveryService;
	
	@ManagedProperty(value = "#{messages}")
	private Messages messages;
	
	private Delivery delivery;

	@PostConstruct
	public void init() {
		delivery = new Delivery();
	}
	
	public String insert() {
		List<String> deliveriesEmail = deliveryService.getDeliveriesEmail();
		for(String email:deliveriesEmail) {
			if(delivery.getEmail().equalsIgnoreCase(email)) {
				messages.showInfoMessage("Can not use this email because it belongs to another delivery");
				return "addDelivery.xhtml";
			}
		}
		if(deliveryService.insert(delivery)) {
			messages.showInfoMessage("Delivery was added successfully");
			delivery = new Delivery();
			return "delivery.xhtml?show=2faces-redirect=true";
		}else {
			messages.showInfoMessage("Something went wrong");
			return "addDelivery.xhtml";
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
	
	
	

}
