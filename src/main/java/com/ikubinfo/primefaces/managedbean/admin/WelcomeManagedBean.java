package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ikubinfo.primefaces.model.admin.Admin;
import com.ikubinfo.primefaces.model.delivery.Delivery;
import com.ikubinfo.primefaces.service.admin.AdminService;
import com.ikubinfo.primefaces.service.delivery.DeliveryService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@SessionScoped
public class WelcomeManagedBean implements Serializable {

	private static final long serialVersionUID = 8305457335890012142L;

	@ManagedProperty(value = "#{deliveryService}")
	private DeliveryService deliveryService;

	@ManagedProperty(value = "#{adminService}")
	private AdminService adminService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	private String email;
	private String password;

	public WelcomeManagedBean() {
		super();
	}

	public String logIn() {
		List<String> deliveriesEmail = deliveryService.getDeliveriesEmail();
		for (String string : deliveriesEmail) {
			if (email.equalsIgnoreCase(string)) {
				List<Delivery> deliveries = deliveryService.getAllDeliveries(null, null);
				for (Delivery delivery : deliveries) {
					if (delivery.getEmail().equalsIgnoreCase(email) && delivery.getPassword().equals(password)) {
						deliveryService.changeWorkingStatus(true, email);
						return "/primefaces/delivery/orders.xhtml?show=2faces-redirect=true";
					}
				}
				messages.showInfoMessage("Password is wrong");
				return "welcome.xhtml";

			}
		}
		if (email.equalsIgnoreCase("artjola.kotorri@gmail.com")) {
			Admin admin = adminService.getAdmin(adminService.getAdminId(email));
			if ((admin.getEmail()).equalsIgnoreCase(email) && (admin.getPassword()).equals(password)) {
				return "allOrders.xhtml?show=2faces-redirect=true";
			} else {
				messages.showInfoMessage("Password is wrong");
				return "welcome.xhtml";
			}
		} else {
			messages.showInfoMessage("Wrong email or password");
			return "welcome.xhtml";
		}

	}

	public String logout() {
		List<Delivery> deliveries = deliveryService.getAllDeliveries(null, null);
		for (Delivery delivery : deliveries) {
			if (delivery.getEmail().equalsIgnoreCase(email) && delivery.getPassword().equals(password)) {
				deliveryService.changeWorkingStatus(false, email);
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				return "/primefaces/admin/welcome.xhtml?faces-redirect=true";
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "welcome.xhtml?faces-redirect=true";
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

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

}
