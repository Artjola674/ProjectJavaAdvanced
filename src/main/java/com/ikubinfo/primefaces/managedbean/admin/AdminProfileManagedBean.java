package com.ikubinfo.primefaces.managedbean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.model.admin.Admin;
import com.ikubinfo.primefaces.service.admin.AdminService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class AdminProfileManagedBean implements Serializable {

	private static final long serialVersionUID = -3483627202295848834L;

	@ManagedProperty(value = "#{adminService}")
	private AdminService adminService;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@ManagedProperty(value = "#{welcomeManagedBean}")
	private WelcomeManagedBean welcomeManagedBean;

	private Admin admin;
	private Admin editAdmin;
	private String oldPassword;
	private String newPassword;

	@PostConstruct
	public void init() {
		admin = adminService.getAdmin(adminService.getAdminId(welcomeManagedBean.getEmail()));
	}

	public void save() {
		if (oldPassword.isEmpty()) {
			editAdmin.setPassword(admin.getPassword());
			if (adminService.save(editAdmin)) {
				oldPassword = null;
				newPassword = null;
				messages.showInfoMessage("Admin was saved sucessfully");
			} else {
				messages.showInfoMessage("Something went wrong");
			}
		} else {
			if (oldPassword.equals(editAdmin.getPassword())) {
				editAdmin.setPassword(newPassword);
				if (adminService.save(editAdmin)) {
					oldPassword = null;
					newPassword = null;
					messages.showInfoMessage("Admin was saved sucessfully");
				} else {
					messages.showInfoMessage("Something went wrong");
				}

			} else {
				oldPassword = null;
				messages.showInfoMessage("Password is incorrect");
			}
		}

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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Admin getEditAdmin() {
		return editAdmin;
	}

	public void setEditAdmin(Admin editAdmin) {
		this.editAdmin = editAdmin;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public WelcomeManagedBean getWelcomeManagedBean() {
		return welcomeManagedBean;
	}

	public void setWelcomeManagedBean(WelcomeManagedBean welcomeManagedBean) {
		this.welcomeManagedBean = welcomeManagedBean;
	}

}
